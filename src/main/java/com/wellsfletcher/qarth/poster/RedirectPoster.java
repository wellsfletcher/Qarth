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
    protected List<String> links;

    public RedirectPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int columns) {
        this(hostURL, contentDirectory, name, inputFilePath, 0, columns);
        setCountToLineCount();
    }

    public RedirectPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int rows, int columns) {
        super(hostURL, contentDirectory, name, inputFilePath, rows, columns);
        // links = createLinks();
    }

    protected List<String> createLinks() {
        return super.getLines();
    }

    protected String getLink(int index) {
        // String link = super.getFileContent(index);
        // System.out.println("Redirect link got.");
        String link = links.get(index % links.size());
        return link;
    }

    @Override
    protected String getFileContent(int index) {
        // change this please
        if (links == null) {
            links = createLinks();
        }

        String link = getLink(index);
        String result = createPageContent(link);

        return result;
    }

    protected String createPageContent(String link) {
      String result = "";

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
