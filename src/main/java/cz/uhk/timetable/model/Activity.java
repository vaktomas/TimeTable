package cz.uhk.timetable.model;

// POJO Timetable activity

import java.time.LocalTime;

public class Activity {
    private String id;          //zkratka predmetu
    private String name;        //cele jmeno modulu
    private String teacher;     //ucitele
    private String day;
    private String type;        //cviceni, prednaska atd.
    private LocalTime from, to;

    public Activity() {
    }

    public Activity(String id, String name, String teacher, String day, String type, LocalTime from, LocalTime to) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.day = day;
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", day='" + day + '\'' +
                ", type='" + type + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
