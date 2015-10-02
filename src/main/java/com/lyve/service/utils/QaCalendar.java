package com.lyve.service.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mmadhusoodan on 11/24/14.
 */
public class QaCalendar {

    private static SimpleDateFormat mmddyyyyyHHMMFormat = new SimpleDateFormat("MM-dd-yyyy-hh:mm");
    private static Calendar calendar = Calendar.getInstance();
    private static QaCalendar instance;

    private QaCalendar() {
    }

    public static synchronized QaCalendar getInstance() {

        if (instance == null) {
            instance = new QaCalendar();
        }

        return instance;
    }

    public String getCaptureTime() {

        final String captureDate = mmddyyyyyHHMMFormat.format(calendar.getTime());
        return captureDate;
    }
}
