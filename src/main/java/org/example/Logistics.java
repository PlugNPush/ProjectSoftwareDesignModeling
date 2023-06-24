package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Logistics {

    public void roomReservation(Session session) {
        System.out.println("Reserved room " + session.location + " for session " + session.title);
    }

    public void validateTrainer(Session session) {
        session.trainer.setGivenCourses(session.trainer.givenCourses + 1);
        System.out.println("Waiting for trainer confirmation at " + session.trainer.email);

        // Sleep for 2 second
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Validated trainer for session " + session.title);
    }

    public void informClients(Session session, List<Registration> registrations) {
        System.out.println("Informed clients " + registrations.stream().map(registration -> registration.client.email).collect(Collectors.toList()) + " for session " + session.title);
        for (Registration registration : registrations) {
            registration.setStatus("confirmed");
        }
    }

    public void sendSchedule(Session session) {
        System.out.println("Sent schedule for session " + session.title);
    }

    public void sendAttendance(Session session) {
        System.out.println("Sent attendance for session " + session.title);
    }

    public void createEvaluation(Session session) {
        System.out.println("Created evaluation for session " + session.title);
    }
}
