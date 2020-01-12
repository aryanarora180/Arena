package com.example.arena2020.items;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleSport {

    public static final long SPORT_CRICKET = 100;
    public static final long SPORT_FOOTBALL = 101;
    public static final long SPORT_SQUASH = 102;

    public static final long MATCH_NOT_STARTED = 1000;
    public static final long MATCH_IN_PROGRESS = 1001;
    public static final long MATCH_COMPLETED = 1002;

    private String documentId;
    private Date dateAndTime;
    private long sportCode;
    private String teamA;
    private String teamB;
    private boolean bookmarked;

    public ScheduleSport(String documentId, Date dateAndTime, long sportCode, String teamA, String teamB, boolean bookmarked) {
        this.documentId = documentId;
        this.dateAndTime = dateAndTime;
        this.sportCode = sportCode;
        this.teamA = teamA;
        this.teamB = teamB;
        this.bookmarked = bookmarked;
    }

    public static String getSportName(long sportCode) {
        if (sportCode == SPORT_CRICKET)
            return "Cricket";
        else if (sportCode == SPORT_FOOTBALL)
            return "Football";
        else if (sportCode == SPORT_SQUASH)
            return "Squash";
        else
            return "Invalid sport";
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

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getVsTeams() {
        return teamA + " vs " + teamB;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
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

    public int getDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return Integer.parseInt(formatter.format(dateAndTime));
    }

    public String getDocumentId() {
        return documentId;
    }
}
