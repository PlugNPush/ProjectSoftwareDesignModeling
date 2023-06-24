package org.example;

import java.util.List;

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
