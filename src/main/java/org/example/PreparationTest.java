package org.example;

public class PreparationTest {
    String content;
    Double grade;

    public PreparationTest(String content) {
        this.content = content;
        this.grade = null;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}