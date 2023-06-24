package org.example;

public class Registration {
    Session session;
    Client client;
    PreparationTest preparationTest;
    Double gradeAssigned;
    Boolean paymentStatus;
    String status;

    public void rateTrainer() {
        System.out.println("Rate trainer");
    }

    public void evaluateSession() {
        System.out.println("Evaluate session");
    }

    public Registration(Session session, Client client, PreparationTest preparationTest) {
        this.session = session;
        this.client = client;
        this.preparationTest = preparationTest;
        this.gradeAssigned = 0.0;
        this.paymentStatus = false;
        this.status = "pending";
    }

    public void setGrade(Double grade) {
        this.gradeAssigned = grade;
    }

    public void setPaymentStatus(Boolean status) {
        this.paymentStatus = status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "{" + this.client.name + ", " + this.session.title + ", " + this.status + "}";
    }
}
