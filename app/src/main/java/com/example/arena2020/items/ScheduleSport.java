package com.example.arena2020.items;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleSport {

    public static final long SPORT_CRICKET = 100;
    public static final long SPORT_FOOTBALL = 101;
    public static final long SPORT_SQUASH = 102;

    private String documentId;
    private Date dateAndTime;
    private long sportCode;
    private String teams;
    private boolean bookmarked;

    public ScheduleSport(String documentId, Date dateAndTime, long sportCode, String teams, boolean bookmarked) {
        this.documentId = documentId;
        this.dateAndTime = dateAndTime;
        this.sportCode = sportCode;
        this.teams = teams;
        this.bookmarked = bookmarked;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public long getSportCode() {
        return sportCode;
    }

    //TODO: implement getSportName() completely and remove hardcoding and use String resources
    public String getSportName() {
        if (sportCode == SPORT_CRICKET)
            return "Cricket";
        else if (sportCode == SPORT_FOOTBALL)
            return "Football";
        else if (sportCode == SPORT_SQUASH)
            return "Squash";
        else
            return "Invalid sport";
    }

    public String getTeams() {
        return teams;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        return simpleDateFormat.format(dateAndTime);
    }

    public String getAoP() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a", Locale.getDefault());
        return simpleDateFormat.format(dateAndTime);
    }

    public String getDocumentId() {
        return documentId;
    }
}
