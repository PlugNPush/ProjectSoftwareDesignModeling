package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
    List<Course> courses;

    public Catalog(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void deleteCourse(String courseTitle) {
        this.courses.removeIf(course -> course.title.equals(courseTitle));
    }

    public void manageCourse(Course course, String action) {
        if (action.equals("edit")) {
            this.deleteCourse(course.title);
            this.addCourse(course);
        } else if (action.equals("delete")) {
            this.deleteCourse(course.title);
        }
    }

    @Override
    public String toString(){
        return this.courses.stream().map(course -> course.title).collect(Collectors.joining(", "));
    }
}
