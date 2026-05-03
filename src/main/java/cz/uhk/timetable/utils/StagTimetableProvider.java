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
    private static final String STAG_URL = "https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=LS&budova=%s&mistnost=%s&outputFormat=JSON"; //adresa pro stazeni jedne mistnosti
    private static final String ROOMS_URL = "https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getMistnostiInfo?zkrBudovy=J&pracoviste=%25&typ=U&outputFormat=JSON&cisloMistnosti=%25"; //adresa ktera vrati seznam vsechn uceben na budove J

    private final Gson gson;

    public StagTimetableProvider() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter()) //až v JSONu uvidi nas cas, pouzije ho
                .create();
    }

    @Override
    public LocationTimetable readTimetable(String building, String room) {
        try {
            var url = new URL(STAG_URL.formatted(building, room)); //vezme sablonu adresy a dosadi do ni konkretni budovu a mistnost
            var reader = new InputStreamReader(url.openStream()); //otevre spojeni k dane adrese
            return gson.fromJson(reader, LocationTimetable.class); //GSON precte data z internetu a automaticky z nich vytvori objekt LocationTimetable se vsemi aktivitami
        } catch (IOException e) { //pokud internet nefunugje vyhodi to chybu
            throw new RuntimeException("Chyba při načítání rozvrhu", e);
        }
    }

    public List<Room> getAllRooms() {
        try {
            var url = new URL(ROOMS_URL);
            var reader = new InputStreamReader(url.openStream());
            RoomList response = gson.fromJson(reader, RoomList.class);
            return (response != null && response.getRooms() != null) ? response.getRooms() : new ArrayList<>(); //pokud se data ze serveru v poradku nacetla, vrati se seznam mistnosti, jinak se vrati prazdny seznam
        } catch (IOException e) { //pokud by se stazeni nepovedlo metoda vrati prazdny seznam
            return new ArrayList<>();
        }
    }
}