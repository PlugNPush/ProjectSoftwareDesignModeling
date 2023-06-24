package org.example;

public class CatalogManager extends Person {
    public void CreateCourse(Catalog catalog, Course course) {
        catalog.addCourse(course);
        System.out.println("Create course");
    }

    public void ManageCourse(Catalog catalog, Course course, String action) {
        catalog.manageCourse(course, action);
        System.out.println("Update course");
    }

    public CatalogManager(String name, String email, String phone, String address) {
        super(name, email, phone, address);
    }
}