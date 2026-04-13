package cz.uhk.timetable.utils;

import cz.uhk.timetable.model.Activity;
import cz.uhk.timetable.model.LocationTimetable;

import java.time.LocalTime;

public class MockTimetableProvider implements ITimetableProvider {


    @Override
    public LocationTimetable readTimetable(String building, String room) {
        var tt = new LocationTimetable("J", "J22");

        tt.getActivities().add(new Activity(
                "PRO1", "Programovani I", "Kozel",
                "Pondeli", "Cviceni",
                LocalTime.of(11, 35),
                LocalTime.of(13,05)
        ));
        tt.getActivities().add(new Activity(
                "PRO1", "Programovani I", "Kozel",
                "Utery", "Prednaska",
                LocalTime.of(9, 05),
                LocalTime.of(10,35)
        ));
        tt.getActivities().add(new Activity(
                "ZMAT2", "Zaklady matematiky II", "Bauer",
                "Pondeli", "Prednaska",
                LocalTime.of(10, 45),
                LocalTime.of(12,15)
        ));

        return tt;
    }
}
