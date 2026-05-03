package cz.uhk.timetable.model;

// POJO Timetable activity

import com.google.gson.annotations.SerializedName;
import java.time.LocalTime;

//rozvrhove akce
public class Activity {
    @SerializedName("predmet")  //nazev pole v JSON
    private String id;          //nas nazev
    @SerializedName("nazev")
    private String name;
    @SerializedName("ucitel")
    private Teacher teacher;
    @SerializedName("denZkr")
    private String day;
    @SerializedName("typAkceZkr")
    private String type;        //cviceni, prednaska atd.
    @SerializedName("hodinaSkutOd")
    private LocalTime from;
    @SerializedName("hodinaSkutDo")
    private LocalTime to;

    public Activity() { //prazdny obejkt do ktereho se pak daji data
    }

    public Activity(String id, String name, Teacher teacher, String day, String type, LocalTime from, LocalTime to) {
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
        if (teacher == null) return "";
        if (teacher != null) {
            return teacher.toString();
        } else {
            return "Nezadáno";
        }
    }

    public void setTeacher(Teacher teacher) {
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
    public String toString() { //muzu si v konzoli overit vsechny informace o te hodine
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
