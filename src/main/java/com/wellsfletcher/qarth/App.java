package com.wellsfletcher.qarth;
import com.wellsfletcher.qarth.gen.*;
import com.wellsfletcher.qarth.poster.*;

import com.wellsfletcher.qarth.spotify.Playlists;
import com.wellsfletcher.qarth.spotify.SpotifyGenerator;
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
    public static void mainTester(String[] args) {
        enumerateIntegerPairsOfAspectRatio(2, 3);
        System.out.println();
        enumerateIntegerPairsOfAspectRatio(3, 4);
        System.out.println();
        enumerateIntegerPairsOfAspectRatio(3, 5);
    }

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
        //- Generator.vertical(links, dir, name);

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

        name = "fletcher-collage-horizontal.png";
        File file = new File(dir + name);
        // SpotifyGenerator.grid(Playlists.COLLEGE_ROOM, file, 7, 5); // BANGKOK_BLUES
        // SpotifyGenerator.grid(Playlists.RAM_RANCH, file, 7, 5);
        // SpotifyGenerator.grid(Playlists.RAM_RANCH, file, 23, 14); // 25, 15
        // SpotifyGenerator.grid(Playlists.RAM_RANCH, file, 12, 8); // 11, 7
        // SpotifyGenerator.grid(Playlists.RYAN_TOP_2020, file, 6, 10);
        // SpotifyGenerator.grid(Playlists.FLETCHER_TOP_2020, file, 9, 6);
        // SpotifyGenerator.grid(Playlists.FLETCHER_TOP_2020, file, 6, 10);

        Poster poster = new GFWDynamicPoster();
        poster.create();
        poster.run();

        /*
        String[][] table = {
            {"https://www.google.com/search?q=poop&sxsrf=ALeKk013AfUsrBOIuep2pgtykzDPtiBtDw:1592456127675&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj1q72uyYrqAhXsUt8KHbM-AHQQ_AUoAXoECBEQAw&biw=1795&bih=921", "https://scontent-ort2-2.xx.fbcdn.net/v/t1.0-9/28279055_109725583190193_9044741175698667605_n.jpg?_nc_cat=108&_nc_sid=09cbfe&_nc_ohc=N1h1-NG7A80AX9-faeH&_nc_ht=scontent-ort2-2.xx&oh=d890b84ba69a3a3ef695e02953536a37&oe=5F137C25", "https://kahoot.it/"},
            {"https://fletcherwells.com/", "https://www.youtube.com/playlist?list=PLXKAG8g1Ls_Ax-SU7rCgyiGWjylB5NHL-", "https://youtu.be/qTUnDV3MgVQ"}
        };
         */
        String[][] table = {
            {"https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/"},
            {"https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/"},
            {"https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/"},
            {"https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/"},
            {"https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/", "https://marbles.fletcherwells.com/"}
        };
        name = "grid-simple.png";
        QRNode[][] codes = QRNode.from(table);
        // Styler.rgbCorners(codes);
        // Styler.randomSettingTones(codes);
        // Styler.randomStoplight(codes);
        //- Styler.testColors(codes);
        // Styler.randomStoplight(codes);
        // Generator.grid(table, dir, name);
        //- Generator.grid(codes, new File(dir + name));

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

    /*
    private static void enumerateIntegerPairsOfAspectRatio(double ratio) {
        enumerateIntegerPairsOfAspectRatio(ratio, 50);
    }

    private static void enumerateIntegerPairsOfAspectRatio(double ratio, int max) {
        int min = 1;

    }
    */
    private static void enumerateIntegerPairsOfAspectRatio(int left, int right) { // left x right
        enumerateIntegerPairsOfAspectRatio(left, right, 100);
    }

    private static void enumerateIntegerPairsOfAspectRatio(int left, int right, int max) {
        int l = left;
        int r = right;
        while (l * r <= max) {
            String pair = l + "x" + r;
            System.out.println(pair);
            l += left;
            r += right;
        }
    }
}
