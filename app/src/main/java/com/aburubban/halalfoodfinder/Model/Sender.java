package com.aburubban.halalfoodfinder.Model;


/**
 * Created by Abu Rubban on 22-Sep-18.
 */

public class Sender {
    public String to;
    public Notification notification;

    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
