package com.wellsfletcher.qarth;

// QR code generation
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

// general
import java.io.*;

// graphics
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

// data structures
import java.util.List;
import java.util.LinkedList;

/**
 * Holds information necessary to generate a single QRCode
 */
public class QRNode extends QRCode {
    // private int width = 250;
    // private int height = 250;
    protected Style style;

    public QRNode(String text) {
        this(text, new Style());
    }

    public QRNode(String text, Style style) {
        super(text);
        setStyle(style);
    }

    /**
     * Creates a QR code image and saves it to the given path
     */
    public void create(String path) {
        create(new File(path));
    }

    /**
     * Creates a QR code image and saves it to the given path
     */
    public void create(File file) {
        String path = file.getPath();
        // String extension = FileSystemUtils.getExtension(file);

        ByteArrayOutputStream bout = this
            .withSize(width, height)
            .to(ImageType.PNG)
            .stream();

        try {
            OutputStream out = new FileOutputStream(path);
            bout.writeTo(out);

            out.flush();
            out.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<QRNode> from(final List<String> links) {
        List<QRNode> result = new LinkedList<>();

        for (String link : links) {
            result.add(new QRNode(link));
        }

        return result;
    }

    public static QRNode[][] from(final String[][] links) {
        QRNode[][] result = new QRNode[links.length][links[0].length];

        for (int j = 0; j < links.length; j++) {
            for (int i = 0; i < links[0].length; i++) {
                result[j][i] = new QRNode(links[j][i]);
            }
        }

        return result;
    }

    public void setStyle(Style style) {
        this.style = style;
        width = style.getWidth();
        height = style.getHeight();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
