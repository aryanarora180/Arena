package com.dota.arena2020.items;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Announcement {

    private String documentId;
    private String announcementName;
    private String announcementDescription;
    private Date announcementDate;

    public Announcement(String documentId, String announcementName, String announcementDescription, Date announcementDate) {
        this.documentId = documentId;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM hh:mm a", Locale.getDefault());
        return simpleDateFormat.format(announcementDate);
    }

}
