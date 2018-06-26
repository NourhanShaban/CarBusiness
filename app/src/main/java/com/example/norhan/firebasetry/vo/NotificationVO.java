package com.example.norhan.firebasetry.vo;

public class NotificationVO {
    private String title;
    private String message;
    private String iconUrl;
    private String action;
    private String actionDestination;
    private int touserid;
    private int tocarid;
    private String notificationType;





    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }


    public int getToCarID() {
        return tocarid;
    }


    public int getToDriverID() {
        return touserid;
    }
    public void setToCarID(int toCarID) {
        this.tocarid = toCarID;
    }

    public void setToDriverID(int toUserID) {
        this.touserid = toUserID;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionDestination() {
        return actionDestination;
    }

    public void setActionDestination(String actionDestination) {
        this.actionDestination = actionDestination;
    }
}