package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;

import java.time.LocalDateTime;

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
