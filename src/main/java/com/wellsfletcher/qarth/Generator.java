package com.wellsfletcher.qarth;

// QR code generation
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

// general
import java.io.*;
// import java.nio.file;
// import java.nio.Path;
import java.nio.file.Paths;

// graphics
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

// data structures
import java.util.List;

/**
 * Generates QR code art.
 */
public final class Generator {
    private Generator() {}

    public static void simple(String link, String directory, String name) {
        String path = FileSystemUtils.join(directory, name);
        simple(link, path);
    }

    public static void simple(String link, String path) {
        QRNode qr = new QRNode(link);
        simple(qr, new File(path));
    }

    public static void simple(QRNode qr, File file) {
        qr.create(file);
    }

    public static void vertical(List<String> links, String directory, String name) {
        String path = FileSystemUtils.join(directory, name);
        vertical(links, path);
    }

    public static void vertical(List<String> links, String path) {
        List<QRNode> qrs = QRNode.from(links);
        File file = new File(path);
        vertical(qrs, file);
    }

    public static void vertical(List<QRNode> links, File file) {
        int length = links.size();
        QRNode[][] grid = new QRNode[length][1];
        for (int k = 0; k < length; k++) {
            grid[k][0] = links.get(k);
        }
        grid(grid, file);
        /*
        String directory = FileSystemUtils.includeTrailingSlash(file.getParent());
        String name = file.getName();
        String path = FileSystemUtils.join(directory, name);
        int width = 250;
        int length = links.size();

        int currentHeight = 0;
        int totalHeight = width * length;
        BufferedImage combined = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = combined.createGraphics();
        for (QRNode link : links) {
            File tempImageFile = new File(FileSystemUtils.join(directory, "_temp.png"));
            simple(link, tempImageFile);

            try {
                BufferedImage image = ImageIO.read(tempImageFile);
                g2d.drawImage(image, 0, currentHeight, null);
                currentHeight += image.getHeight();
            } catch (IOException e) {
                e.printStackTrace();
            }

            tempImageFile.delete();
        }
        g2d.dispose();

        try {
            // ImageIO.write(combined, "png", new File(path)); // export concat image
            ImageIO.write(combined, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public static void horizontal(List<String> links, String directory, String name) {
        String path = FileSystemUtils.join(directory, name);
        horizontal(links, path);
    }

    public static void horizontal(List<String> links, String path) {
        List<QRNode> qrs = QRNode.from(links);
        File file = new File(path);
        horizontal(qrs, file);
    }

    public static void horizontal(List<QRNode> links, File file) {
        int length = links.size();
        QRNode[][] grid = new QRNode[1][length];
        QRNode[] row = new QRNode[length];
        row = links.toArray(row);
        grid[0] = row;
        grid(grid, file);
    }

    public static void grid(String[][] links, String directory, String name) {
        String path = FileSystemUtils.join(directory, name);
        grid(links, path);
    }

    public static void grid(String[][] links, String path) {
        QRNode[][] qrs = QRNode.from(links);
        File file = new File(path);
        grid(qrs, file);
    }

    /**
     * Generates an image of a grid of QR codes.
     * @requires all the QR codes to be the same size
     */
    public static void grid(QRNode[][] links, File file) {
        String directory = FileSystemUtils.includeTrailingSlash(file.getParent());
        String name = file.getName();
        String extension = "";
        String path = FileSystemUtils.join(directory, name);

        int width = links[0][0].getWidth();
        int height = links[0][0].getHeight();
        int rows = links.length;
        int columns = links[0].length;

        int currentHeight = 0;
        int currentWidth = 0;
        int totalHeight = height * rows;
        int totalWidth = width * columns;
        BufferedImage combined = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = combined.createGraphics();
        for (QRNode[] linkRow : links) {
            for (QRNode link : linkRow) {
                String tempImagePath = FileSystemUtils.join(directory, "_temp.png");
                File tempImageFile = new File(tempImagePath);
                simple(link, tempImageFile);

                try {
                    BufferedImage image = ImageIO.read(tempImageFile);
                    g2d.drawImage(image, currentHeight, currentWidth, null);
                    currentHeight += image.getHeight();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                tempImageFile.delete();
            }
            currentHeight = 0;
            // currentWidth += image.getWidth();
            currentWidth += width;
        }
        g2d.dispose();

        try {
            ImageIO.write(combined, "png", file); // export concat image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
