package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Represents a poster of QR codes, with a text file of links used as the input.
 */
public class RedirectPoster extends FilePoster {
    public RedirectPoster(String hostURL, String contentDirectory, String name) {
        super(hostURL, contentDirectory, name);
    }

    public RedirectPoster(String hostURL, String contentDirectory, String name, String inputFilePath) {
        super(hostURL, contentDirectory, name, inputFilePath);
    }

    public RedirectPoster(String hostURL, String contentDirectory, String name, int columns) {
        super(hostURL, contentDirectory, name, columns);
    }

    public RedirectPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int columns) {
        super(hostURL, contentDirectory, name, inputFilePath, columns);
    }

    protected String getFileContent(int index) {
        String result = "";
        String link = super.getFileContent(index);

        result += "<!DOCTYPE html>";
        result += "<html>";
            result += "<head>";
                result += "<meta http-equiv=\"refresh\" content=\"0; url='" + link + "'\" />";
            result += "</head>";
            result += "<body>";
                result += "<p>";
                    result += "Click ";
                    // result += "<a href=\"https://www.w3docs.com\">";
                    result += "<a href=\"" + link + "\">";
                        result += "here";
                    result += "</a>";
                    result += " if this page does not load.";
                result += "</p>";
            result += "</body>";
        result += "</html>";

        return result;
    }
}
