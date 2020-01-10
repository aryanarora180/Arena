package com.example.arena2020.items;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleSport {

    public static final int SPORT_COLOR_CRICKET = 100;
    public static final int SPORT_COLOR_FOOTBALL = 101;
    public static final int SPORT_COLOR_SQUASH = 102;

    private Date dateAndTime;
    private String name;
    private String subtitle;
    private boolean bookmarked;
    private int color;

    public ScheduleSport(Date dateAndTime, String name, String subtitle, boolean bookmarked, int color) {
        this.dateAndTime = dateAndTime;
        this.name = name;
        this.subtitle = subtitle;
        this.bookmarked = bookmarked;
        this.color = color;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public int getColor() {
        return color;
    }

    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        return simpleDateFormat.format(dateAndTime);
    }

    public String getAoP() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a", Locale.getDefault());
        return simpleDateFormat.format(dateAndTime);
    }

}
