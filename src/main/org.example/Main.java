package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

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

    public class Company {
        String name;
        String email;

        public Company(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

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

    public interface Theme {
        List<String> getThemes();
    }

    public interface Topic {
        List<String> getTopics();
    }

    public interface SubTopic {
        List<String> getSubTopics();
    }

    public class Course implements Theme, Topic, SubTopic {
        String title;
        float price;
        String content;
        List<String> themes;
        List<String> topics;
        List<String> subTopics;

        public Course(String title, float price, String content, List<String> themes, List<String> topics, List<String> subTopics) {
            this.title = title;
            this.price = price;
            this.content = content;
            this.themes = themes;
            this.topics = topics;
            this.subTopics = subTopics;
        }

        @Override
        public List<String> getThemes() {
            return this.themes;
        }

        @Override
        public List<String> getTopics() {
            return this.topics;
        }

        @Override
        public List<String> getSubTopics() {
            return this.subTopics;
        }

        @Override
        public String toString(){
            return this.title;
        }
    }

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

    public class Calendar {
        List<Session> sessions;

        public Calendar(List<Session> sessions) {
            this.sessions = sessions;
        }

        public void addSession(Session session) {
            this.sessions.add(session);
        }

        public void deleteSession(String sessionTitle) {
            this.sessions.removeIf(session -> session.title.equals(sessionTitle));
        }

        public List<Session> getSessionsByCourse(String courseTitle) {
            return this.sessions.stream().filter(session -> session.course.title.equals(courseTitle)).collect(Collectors.toList());
        }

        public Session getNextSessionByCourse(String courseTitle) {
            return this.getSessionsByCourse(courseTitle).stream().filter(session -> session.date.after(new Date())).findFirst().orElse(null);
        }
    }

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


    public static void main(String[] args) {

        System.out.println("Demonstration of adding a client to a session\n\n");
        Main main = new Main();
        main.run();

    }

    public void run() {
        // PART 1 - INITIALIZATION

        // Create a handful of courses
        Course course1 = new Course("Introduction to UML", 350, "Learn to design diagrams", List.of("IT and Technology"), List.of("UML"), List.of("Diagrams"));
        Course course2 = new Course("Introduction to Java", 650, "Learn to code in Java", List.of("IT and Technology"), List.of("Java"), List.of("Programming"));
        Course course3 = new Course("Introduction to C#", 400, "Learn to code in C#", List.of("IT and Technology"), List.of("C#"), List.of("Programming"));
        Course course4 = new Course("Introduction to Python", 250, "Learn to code in Python", List.of("IT and Technology"), List.of("Python"), List.of("Programming"));
        Course course5 = new Course("Introduction to C++", 380, "Learn to code in C++", List.of("IT and Technology"), List.of("C++"), List.of("Programming"));
        Course course6 = new Course("Introduction to PHP", 420, "Learn to code in PHP", List.of("IT and Technology"), List.of("PHP"), List.of("Programming"));
        Course course7 = new Course("Introduction to JavaScript", 650, "Learn to code in JavaScript", List.of("IT and Technology"), List.of("JavaScript"), List.of("Programming"));
        Course course8 = new Course("Introduction to HTML", 215, "Learn to code in HTML", List.of("IT and Technology"), List.of("HTML"), List.of("Programming"));
        Course course9 = new Course("Introduction to CSS", 190, "Learn to code in CSS", List.of("IT and Technology"), List.of("CSS"), List.of("Programming"));
        Course course10 = new Course("Introduction to SQL", 440, "Learn to code in SQL", List.of("IT and Technology"), List.of("SQL"), List.of("Programming"));

        // Create one session for each course
        Session session1 = new Session(course1, "Introduction to UML", new Date("06/30/2023 19:30:00"), "Efrei Bat. A A402", 20, "Planned", "In person", false);
        Session session2 = new Session(course2, "Introduction to Java", new Date("07/04/2023 08:00:00"), "Efrei Bat. A A202", 20, "Planned", "In person", false);
        Session session3 = new Session(course3, "Introduction to C#", new Date("07/05/2023 07:30:00"), "Efrei Bat. A A202", 20, "Planned", "In person", false);
        Session session4 = new Session(course4, "Introduction to Python", new Date("07/12/2023 14:00:00"), "Efrei Bat. A A306", 20, "Planned", "In person", false);
        Session session5 = new Session(course5, "Introduction to C++", new Date("07/16/2023 17:15:00"), "Efrei Bat. B B001", 20, "Planned", "In person", false);
        Session session6 = new Session(course6, "Introduction to PHP", new Date("07/22/2023 09:00:00"), "Efrei Bat. B B108", 20, "Planned", "In person", false);
        Session session7 = new Session(course7, "Introduction to JavaScript", new Date("07/29/2023 16:00:00"), "Efrei Bat. A A401", 20, "Planned", "In person", false);
        Session session8 = new Session(course8, "Introduction to HTML", new Date("08/04/2023 10:30:00"), "Efrei Bat. C C001", 20, "Planned", "In person", false);
        Session session9 = new Session(course9, "Introduction to CSS", new Date("08/09/2023 10:00:00"), "Efrei Bat. A A001", 20, "Planned", "In person", false);
        Session session10 = new Session(course10, "Introduction to SQL", new Date("08/11/2023 13:00:00"), "Efrei Bat. E E103", 20, "Planned", "In person", false);

        // Construct the calendar
        Calendar calendar = new Calendar(List.of(session1, session2, session3, session4, session5, session6, session7, session8, session9, session10));

        // Create the catalog
        Catalog catalog = new Catalog(List.of(course1, course2, course3, course4, course5, course6, course7, course8, course9, course10));

        // Create the company of the client
        Company company = new Company("Efrei Executive", "eee@efrei.fr");

        // Create our client
        Client client = new Client("Jean", "jean@wanadoo.fr", "+33 6 12 34 56 78", "12 avenue des Champs Elysées", false, company);

        // Create the Catalog Manager
        CatalogManager catalogManager = new CatalogManager("John", "john@rdx.net", "+33 6 12 34 56 78", "12 avenue des Champs Elysées");

        // Create a Trainer
        Trainer trainer = new Trainer("Jack", "jack@eu-caliptus.lt", "+33 6 12 34 56 78", "12 avenue des Champs Elysées", "Proficient in Management and IT");

        List<Registration> registrations = new ArrayList<>();
        List<Registration> currentRegistrationsForSession;


        // PART 2 - SEQUENCE

        // The client requests the catalog
        System.out.println("------ The client requests the catalog ------");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Course> courses = catalog.getCourses();
        // The catalog is displayed
        System.out.println("Catalog:" + courses);
        System.out.println("\n");

        // Sleep for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The client chooses a course
        Course course = courses.get(7);

        // The client requests details about the course
        System.out.println("------ The client requests details about a chosen course ------");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> themes = course.getThemes();
        List<String> topics = course.getTopics();
        List<String> subTopics = course.getSubTopics();
        // The details are displayed
        System.out.println("Title: " + course.title + ", Price: " + course.price + "€");
        System.out.println("Themes: " + themes);
        System.out.println("Topics: " + topics);
        System.out.println("Subtopics: " + subTopics);
        System.out.println("\n");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The client has a question about the course
        System.out.println("------ The client has a question about the course ------");
        System.out.println("For any question, please reach out to the Catalog Manager at: " + catalogManager.email);
        System.out.println("\n");

        // Sleep for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The client requests the next session of the course
        System.out.println("------ The client requests the next session of the course ------");
        Session nextSession = calendar.getNextSessionByCourse(course.title);

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The next session is displayed
        System.out.println("Next session: " + nextSession);
        System.out.println("\n");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The user needs to do the preparatory test
        System.out.println("------ The user needs to do the preparatory test ------");

        // Sleep for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PreparationTest preparationTest = client.takePreparationTest(course.title);
        preparationTest.setGrade(12.5);
        System.out.println("The client has taken the test for " + course.title + " and got a grade of: " + preparationTest.grade);
        System.out.println("\n");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The client registers to the next session
        System.out.println("------ The client registers to the next session ------");

        // Sleep for 3 second
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Registration registration = new Registration(nextSession, client, preparationTest);
        registration.setPaymentStatus(true);
        registrations.add(registration);
        System.out.println("The client has registered and payed for the next session: " + nextSession);
        currentRegistrationsForSession = registrations.stream().filter(r -> r.session.equals(nextSession)).collect(Collectors.toList());
        System.out.println(currentRegistrationsForSession);
        System.out.println("\n");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the logistics for the session
        System.out.println("------ Start the logistics for the session ------");

        // Sleep for 3 second
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentRegistrationsForSession = registrations.stream().filter(r -> r.session.equals(nextSession)).collect(Collectors.toList());
        nextSession.setTrainer(trainer);
        Logistics logistics = new Logistics();
        logistics.roomReservation(nextSession);

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logistics.validateTrainer(nextSession);

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logistics.informClients(nextSession, currentRegistrationsForSession);

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logistics.sendSchedule(nextSession);

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logistics.sendAttendance(nextSession);

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logistics.createEvaluation(nextSession);

        System.out.println("\n\n");

        // Sleep for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The client is registred and can now take the course
        System.out.println("The client is now registered to the course: " + course.title);
        System.out.println(currentRegistrationsForSession);


    }
}
