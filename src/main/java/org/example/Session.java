package org.example;

import java.util.Date;

public class Session {
    Course course;
    String title;
    Date date;
    String location;
    int headcount;
    String status;
    String type;
    Boolean isCustom;
    Trainer trainer;

    public Session(Course course, String title, Date date, String location, int headcount, String status, String type, Boolean isCustom) {
        this.course = course;
        this.title = title;
        this.date = date;
        this.location = location;
        this.headcount = headcount;
        this.status = status;
        this.type = type;
        this.isCustom = isCustom;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString(){
        return "{" + this.title + ", " + this.date + ", " + this.location + "}";
    }
}