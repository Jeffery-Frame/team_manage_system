package com.wanxi.springboot.team.manage.system.model;

import java.io.Serializable;

public class Weekly implements Serializable {
    private String reporter;
    private String date;
    private String content;

    public Weekly() {
    }

    public Weekly(String reporter, String date, String content) {
        this.reporter = reporter;
        this.date = date;
        this.content = content;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Weekly{" +
                "reporter='" + reporter + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}