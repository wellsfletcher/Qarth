package com.wellsfletcher.qarth;

// QR code generation
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

// graphics
import java.awt.Color;

/**
 * Holds information for styling a QR code.
 */
public class Style {
    private int width = 125;
    private int height = 125;
    private int margin = 4;
    private int color = 0;
    private int backgroundColor = -1; // Color.white;

    /*
    private class Attribute {
        T type;
        Object value;
        private Attribute(T type, Object value) {
            this.type = type;
            this.value = value;
        }
    }
    Set<Attribute> attributes = new HashSet();
    */

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

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMargin() {
        return this.margin;
    }

    public int getColor() {
        return this.color;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
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
