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
    private int width;
    private int height;
    private int margin = 8;
    private int color = 0xFF000000;
    private int backgroundColor = 0xFFFFFFFF;// -1; // Color.white;

    /*
    Map<StyleType, Object> attributes = new HashMap();
    */

    public Style() {
        this(125, 125);
    }

    public Style(int width, int height) {
        setSize(width, height);
        // setBackgroundColor(new Color(0x0FFFFFFF, true));
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

    public void setColor(Color color) {
        setColor(toInt(color));
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setBackgroundColor(Color backgroundColor) {
        setBackgroundColor(toInt(backgroundColor));
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

    private int toInt(Color color) {
        return color.hashCode();
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
