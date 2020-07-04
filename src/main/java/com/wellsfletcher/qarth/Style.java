package com.wellsfletcher.qarth;

// QR code generation
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

/**
 * Holds information for styling a QR code.
 */
public class Style {
    private int width = 125;
    private int height = 125;

    public Style() {

    }

    public Style(int width, int height) {
        setSize(width, height);
    }

    public void setWidth(int width) {
        setSize(width, height);
    }

    public void setHeight(int height) {
        setSize(width, height);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        String result = "";
        String SEPARATOR = ", ";

        result += "width = " + width;
        result += SEPARATOR;
        result += "height = " + height;
        // result += SEPARATOR;

        return result;
    }
}
