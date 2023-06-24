package org.example;

public class Client extends Person {
    Boolean isInternal;
    Company company;

    public Client(String name, String email, String phone, String address, Boolean isInternal, Company company) {
        super(name, email, phone, address);
        this.isInternal = isInternal;
        this.company = company;
    }

    public PreparationTest takePreparationTest(String content) {
        return new PreparationTest(content);
    }
}