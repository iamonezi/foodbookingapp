package com.phunlh2001.prm392_beverages.utils;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static String dateToTimestamp(Date date) {
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
        return date == null ? null : formats.format(date);
    }

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value == null) return new Date();
        Date date = new Date();
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formats.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
