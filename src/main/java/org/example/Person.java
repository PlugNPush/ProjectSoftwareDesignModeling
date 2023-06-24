package org.example;

public abstract class Person {
    String name;
    String email;
    String phone;
    String address;

    public Person(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
