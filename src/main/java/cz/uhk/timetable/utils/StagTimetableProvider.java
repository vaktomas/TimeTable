package cz.uhk.timetable.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.uhk.timetable.model.LocationTimetable;
import cz.uhk.timetable.model.Room;
import cz.uhk.timetable.model.RoomList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


//stahuje JSON a prevadi ho na objekty
public class StagTimetableProvider implements ITimetableProvider {
    private static final String STAG_URL = "https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=LS&budova=%s&mistnost=%s&outputFormat=JSON";
    private static final String ROOMS_URL = "https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getMistnostiInfo?zkrBudovy=J&pracoviste=%25&typ=U&outputFormat=JSON&cisloMistnosti=%25";

    private final Gson gson;

    public StagTimetableProvider() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
    }

    @Override
    public LocationTimetable readTimetable(String building, String room) {
        try {
            var url = new URL(STAG_URL.formatted(building, room));
            var reader = new InputStreamReader(url.openStream());
            return gson.fromJson(reader, LocationTimetable.class);
        } catch (IOException e) {
            throw new RuntimeException("Chyba při načítání rozvrhu", e);
        }
    }

    public List<Room> getAllRooms() {
        try {
            var url = new URL(ROOMS_URL);
            var reader = new InputStreamReader(url.openStream());
            RoomList response = gson.fromJson(reader, RoomList.class);
            return (response != null && response.getRooms() != null) ? response.getRooms() : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}