package com.aburubban.halalfoodfinder.Model;

/**
 * Created by Abu Rubban on 22-Sep-18.
 */

public class Notification {
    public String body;
    public String title;

    public Notification(String body, String title) {
        this.body = body;
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
