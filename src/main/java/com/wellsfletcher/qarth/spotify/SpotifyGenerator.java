package com.wellsfletcher.qarth.spotify;

import com.wellsfletcher.qarth.util.FileSystem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SpotifyGenerator {
    protected static SpotifyManager spotify = new SpotifyManager();

    /*
    public static void grid(String playlistId, File file, int rows, int columns) {
        grid(playlistId, file, columns);
    }
     */

    public static void grid(String playlistId, File file, int rows, int columns) {
        List<String> links = spotify.getAlbumCoverUrlsFromPlaylist(playlistId);

        // remove duplicates from the list
        links = removeDuplicates(links);

        // loop the list to ensure that it is at least the desired length
        links = loopList(links, rows * columns);
        links = trimList(links, rows * columns);

        String[][] grid = (String[][]) listToGrid(links, columns);
        gridFromUrls(grid, file);
    }

    private static <T> List<T> removeDuplicates(List<T> list) {
        List<T> result = new LinkedList<>();
        Set<T> visited = new HashSet<T>();

        for (T item : list) {
            if (!visited.contains(item)) {
                result.add(item);
                visited.add(item);
            }
        }

        return result;
    }

    public static void gridFromUrls(String[][] links, File file) {
        String directory = FileSystem.includeTrailingSlash(file.getParent());
        String name = file.getName();
        String extension = FileSystem.getExtension(file);
        String path = FileSystem.join(directory, name);

        // int width = links[0][0].getWidth();
        // int height = links[0][0].getHeight();
        int width = 640;
        int height = 640;
        int rows = links.length;
        int columns = links[0].length;

        int totalHeight = height * rows;
        int totalWidth = width * columns;
        BufferedImage combined = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combined.createGraphics();

        int currentHeight = 0;
        for (String[] linkRow : links) {
            int currentWidth = 0;
            int actualHeight = 0;
            for (String link : linkRow) {
                int actualWidth = 0;
                if (link != null) {
                    String tempImagePath = FileSystem.join(directory, "_temp" + "." + extension);
                    File tempImageFile = new File(tempImagePath);
                    // simple(link, tempImageFile);

                    try {
                        URL url = new URL(link);
                        BufferedImage image = ImageIO.read(url);
                        // if the image is not the target dimensions, then it needs to be resized/cropped here
                        try {
                            image = fixImage(image, width, height);
                        } catch (Exception e) {
                            System.out.println("ope");
                        }
                        g2d.drawImage(image, currentWidth, currentHeight, null);
                        actualWidth = image.getWidth();
                        actualHeight = image.getHeight();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    tempImageFile.delete();
                } else {
                    // currentWidth += width;
                }
                // currentWidth += actualWidth;
                currentWidth += width;
            }
            // currentHeight += actualHeight;
            currentHeight += height;
        }
        g2d.dispose();

        try {
            ImageIO.write(combined, extension, file); // export concat image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage fixImage(BufferedImage image, int targetWidth, int targetHeight) { // I don't think this works when the target dimensions are not square
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        if (imageHeight == targetHeight && imageWidth == targetHeight) return image; // not necessary but makes it run faster
        // resize the image such that there is either extraneous height or extraneous  width or the image has the target dimensions
        // resize the image such that either (imageWidth == targetWidth and height >)
        int newWidth = 0;
        int newHeight = 0;
        if (imageHeight > imageWidth) { // if (targetWidth > targetHeight) {
            double ratio = (((double) imageWidth) / imageHeight);
            newWidth = targetWidth;
            newHeight = (int) (ratio * targetWidth);
        } else if (imageWidth > imageHeight) {
            double ratio = (((double) imageWidth) / imageHeight);
            newWidth = (int) (ratio * targetHeight);
            newHeight = targetHeight;
        } else { // this don't work if targetWidth != targetHeight
            newWidth = targetWidth;
            newHeight = targetHeight;
        }
        image = resizeImage(image, newWidth, newHeight);
        // crop the extraneou
        image = centeredCropImage(image, targetWidth, targetHeight);
        // System.out.println("width = " + image.getWidth());
        // System.out.println("height = " + image.getHeight());
        return image;
    }

    private static BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private static BufferedImage centeredCropImage(BufferedImage src, int w, int h) {
        int imageWidth = src.getWidth();
        int imageHeight = src.getHeight();
        int x = (imageWidth - w) / 2;
        int y = (imageHeight - h) / 2;
        return cropImage(src, x, y, w, h);
    }

    private static BufferedImage cropImage(BufferedImage src, int x, int y, int w, int h) {
        BufferedImage dest = src.getSubimage(x, y, w, h);
        return dest;
    }

    /*
    protected static <T> T[][] listToGrid(List<T> codes, int columns) {
        int length = codes.size();
        int rows = (int) Math.ceil(((double) length) / columns);
        // int rows = (int) ((((double) length) / columns) + 0.5);
        // T[][] grid = (T[][]) new Object[rows][columns];
        T[][] grid = (T[][]) new Object[rows][columns];
        int k = 0;
        for (T qr : codes) {
            int row = (int) k / columns;
            int column = k % columns;
            grid[row][column] = qr;
            k++;
        }

        return grid;
    }
     */
    protected static String[][] listToGrid(List<String> codes, int columns) {
        int length = codes.size();
        int rows = (int) Math.ceil(((double) length) / columns);
        // int rows = (int) ((((double) length) / columns) + 0.5);
        // T[][] grid = (T[][]) new Object[rows][columns];
        String[][] grid = (String[][]) new String[rows][columns];
        int k = 0;
        for (String qr : codes) {
            int row = (int) k / columns;
            int column = k % columns;
            grid[row][column] = qr;
            k++;
        }

        return grid;
    }

    protected static <T> List<T> trimList(final List<T> list, int trimmedLength) {
        List<T> result = new LinkedList<>();
        for (int k = 0; k < trimmedLength; k++) {
            result.add(list.get(k));
        }
        return result;
    }

    protected static <T> List<T> loopList(final List<T> list, int targetLength) {
        if (list.size() >= targetLength) return list;

        List<T> result = new LinkedList<>();
        for (int k = 0; k < targetLength; k++) {
            result.add(list.get(k % targetLength));
        }
        return result;
    }
}
