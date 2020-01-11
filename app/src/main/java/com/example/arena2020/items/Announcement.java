package com.example.arena2020.items;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Announcement {

    private String announcementName;
    private String announcementDescription;
    private Date announcementDate;

    public Announcement(String announcementName, String announcementDescription, Date announcementDate) {
        this.announcementName = announcementName;
        this.announcementDescription = announcementDescription;
        this.announcementDate = announcementDate;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy hh:mm", Locale.getDefault());
        return simpleDateFormat.format(announcementDate);
    }

}
