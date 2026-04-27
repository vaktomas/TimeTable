package cz.uhk.timetable.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// pomocna trida pro GSON
public class RoomList {
    @SerializedName("mistnostInfo")
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}