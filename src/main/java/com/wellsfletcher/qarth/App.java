package com.wellsfletcher.qarth;
import com.wellsfletcher.qarth.gen.*;
import com.wellsfletcher.qarth.poster.*;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import java.io.*;

import java.util.List;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println( "Hello World!" );

        String input = "/Users/wellsfletcher/Documents/OtherCode/Java/Inputs/";
        String dir = "/Users/wellsfletcher/Documents/OtherCode/Java/Ouputs/";
        String name = "simple.png";
        Generator.simple("https://youtu.be/dQw4w9WgXcQ", dir, name);

        name = "vertical.png";
        List<String> links = new LinkedList<>();
        links.add("https://youtu.be/dQw4w9WgXcQ");
        links.add("https://stackoverflow.com/questions/601274/how-do-i-properly-load-a-bufferedimage-in-java");
        links.add("https://www.google.com/search?q=poop&sxsrf=ALeKk013AfUsrBOIuep2pgtykzDPtiBtDw:1592456127675&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj1q72uyYrqAhXsUt8KHbM-AHQQ_AUoAXoECBEQAw&biw=1795&bih=921");
        Generator.vertical(links, dir, name);

        /*
        String[][] table = {
            {"https://www.google.com/", "https://scontent-ort2-2.xx.fbcdn.net/v/t1.0-9/28279055_109725583190193_9044741175698667605_n.jpg?_nc_cat=108&_nc_sid=09cbfe&_nc_ohc=N1h1-NG7A80AX9-faeH&_nc_ht=scontent-ort2-2.xx&oh=d890b84ba69a3a3ef695e02953536a37&oe=5F137C25", "https://www.google.com/"},
            {"https://www.google.com/", "https://www.google.com/", "https://www.google.com/"}
        };
        */

        /*
        name = "grid-list.png";
        links = new LinkedList<>();
        int rows = 7;
        int columns = 7;
        String host = "https://wellsfletcher.com/qr/a/";
        String SEPARATOR = "x";
        int padding = 2;
        for (int k = 0; k < rows; k++) {
            for (int p = 0; p < columns; p++) {
                String id = "";
                id += String.format("%0" + padding + "d", p);
                id += SEPARATOR;
                id += String.format("%0" + padding + "d", k);
                links.add(host + id);
            }
        }
        Generator.grid(links, dir, name, columns);
        */
        // DynamicPoster manager = new DynamicPoster();
        // manager.poster(28, 13);
        // name = "bee.png";
        // Generator.fromFile("/Users/wellsfletcher/Documents/OtherCode/HTML/New-Personal-Website/qr/b/text.txt", dir, name, 13);
        // name = "phantom.png";
        // Generator.fromFile(input + "phantom.txt", dir, name, 27);

        Poster poster = new GFWDynamicPoster();
        poster.create();


        String[][] table = {
            {"https://www.google.com/search?q=poop&sxsrf=ALeKk013AfUsrBOIuep2pgtykzDPtiBtDw:1592456127675&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj1q72uyYrqAhXsUt8KHbM-AHQQ_AUoAXoECBEQAw&biw=1795&bih=921", "https://scontent-ort2-2.xx.fbcdn.net/v/t1.0-9/28279055_109725583190193_9044741175698667605_n.jpg?_nc_cat=108&_nc_sid=09cbfe&_nc_ohc=N1h1-NG7A80AX9-faeH&_nc_ht=scontent-ort2-2.xx&oh=d890b84ba69a3a3ef695e02953536a37&oe=5F137C25", "https://kahoot.it/"},
            {"https://fletcherwells.com/", "https://www.youtube.com/playlist?list=PLXKAG8g1Ls_Ax-SU7rCgyiGWjylB5NHL-", "https://youtu.be/qTUnDV3MgVQ"}
        };
        name = "grid-simple.png";
        Generator.grid(table, dir, name);

        System.out.println("Finished.");
    }

    private static String cipher(String input) {
        String result = "";
        int shift = 3;
        for (int k = 0; k < input.length(); k++) {
            char curr = input.charAt(k);
            result += (char) (((int) curr) + shift);
        }
        return result;
    }
}
