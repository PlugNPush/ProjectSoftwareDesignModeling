package org.example;

public class Trainer extends Person {
    String resume;
    int givenCourses;
    float grades;
    Boolean qualitywarning;

    public Trainer(String name, String email, String phone, String address, String resume) {
        super(name, email, phone, address);
        this.resume = resume;
    }

    public void setQualityWarning(Boolean warning) {
        this.qualitywarning = warning;
    }

    public void setGrades(float grades) {
        this.grades = grades;
    }

    public void setGivenCourses(int courses) {
        this.givenCourses = courses;
    }
}