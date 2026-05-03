package cz.uhk.timetable.model;

import com.google.gson.annotations.SerializedName;

// reprezentuje ucitele, slouzi ke spravnemu zapisu
public class Teacher {
    @SerializedName("jmeno")
    private String firstName;
    @SerializedName("prijmeni")
    private String lastName;
    @SerializedName("titulPred")
    private String title;

    @Override
    public String toString() {
        // posklada jmeno "Mgr. Jan Novák"
        String t = "";
        if (title != null && !title.isEmpty()) { //zkontroluje title a jestli  v nem neco je
            t = title + " ";
        }
        return t + firstName + " " + lastName;
    }
}