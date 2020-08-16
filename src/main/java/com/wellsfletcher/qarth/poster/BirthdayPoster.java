package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.poster.schedule.ModPattern;
import com.wellsfletcher.qarth.poster.schedule.Schedule;
import com.wellsfletcher.qarth.poster.schedule.TemporalExpression;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Represents a poster of QR codes, with a text file used as input.
 */
public class BirthdayPoster extends XMLPoster {
    LocalDateTime date;

    public BirthdayPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int columns) {
        this(hostURL, contentDirectory, name, inputFilePath, 0, columns);
        // define automatic count here
    }

    public BirthdayPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int rows, int columns) {
        super(hostURL, contentDirectory, name, inputFilePath, rows, columns);
        schedule();
    }

    protected String getFileContent(int index) {
        return super.getFileContent(index);
    }

    protected void schedule() {
        date = LocalDateTime.parse("2020-08-16 09:00:05", TemporalExpression.formatter);
        schedule.annually(this, date);
        /*
        TemporalExpression pattern = new ModPattern(Schedule.now(), 4, 3);
        schedule.add(this, pattern);

         */
    }
}
