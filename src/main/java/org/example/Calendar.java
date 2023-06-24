package org.example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
