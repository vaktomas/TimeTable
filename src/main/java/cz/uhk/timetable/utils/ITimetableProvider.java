package cz.uhk.timetable.utils;

import cz.uhk.timetable.model.LocationTimetable;

// Interface for timetable provider classes

public interface ITimetableProvider {
    LocationTimetable readTimetable (String building, String room); //parametry pro vyhledavani
}
