package com.wellsfletcher;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import java.io.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        ByteArrayOutputStream bout = QRCode.from("https://memorynotfound.com")
            .withSize(250, 250)
            .to(ImageType.PNG)
            .stream();
        try {
            OutputStream out = new FileOutputStream("/Users/wellsfletcher/Documents/OtherCode/Java/Ouputs/qrcode.png");
            bout.writeTo(out);
            out.flush();
            out.close();
            System.out.println("Success!");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished.");
    }
}
