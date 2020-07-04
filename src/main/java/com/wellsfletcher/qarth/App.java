package com.wellsfletcher.qarth;
// import com.wellsfletcher.qarth.*;

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

        String dir = "/Users/wellsfletcher/Documents/OtherCode/Java/Ouputs/";
        String name = "qrboy.jpg";
        // Generator.simple("https://youtu.be/dQw4w9WgXcQ", dir, "qr1.png");

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

        String[][] table = {
            {"https://www.google.com/search?q=poop&sxsrf=ALeKk013AfUsrBOIuep2pgtykzDPtiBtDw:1592456127675&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj1q72uyYrqAhXsUt8KHbM-AHQQ_AUoAXoECBEQAw&biw=1795&bih=921", "https://scontent-ort2-2.xx.fbcdn.net/v/t1.0-9/28279055_109725583190193_9044741175698667605_n.jpg?_nc_cat=108&_nc_sid=09cbfe&_nc_ohc=N1h1-NG7A80AX9-faeH&_nc_ht=scontent-ort2-2.xx&oh=d890b84ba69a3a3ef695e02953536a37&oe=5F137C25", "https://kahoot.it/"},
            {"https://fletcherwells.com/", "https://www.youtube.com/playlist?list=PLXKAG8g1Ls_Ax-SU7rCgyiGWjylB5NHL-", "https://youtu.be/qTUnDV3MgVQ"}
        };
        name = "qrboy2.png";
        Generator.grid(table, dir, name);

        System.out.println("Finished.");
    }
}
