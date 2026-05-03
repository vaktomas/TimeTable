package cz.uhk.timetable.model;

import com.google.gson.annotations.SerializedName;

// jedna mistnost v systemu
public class Room {
    @SerializedName("zkrBudovy")    //JSON jmeno
    private String building;        //moje promenna
    @SerializedName("cisloMistnosti")
    private String roomNumber;

    @Override
    public String toString() {
        return building + " / " + roomNumber;
    } //hezky text napr.: J/J22

    public String getBuilding() { return building; }
    public String getRoomNumber() { return roomNumber; }
}