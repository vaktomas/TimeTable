package cz.uhk.timetable.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;

//adapter pro GSON, prevadi JSON format casu na objekt localtime
public class LocalTimeAdapter extends TypeAdapter<LocalTime> {
    @Override
    public void write(JsonWriter jsonWriter, LocalTime localTime) throws IOException {
        //funguje opacne (prevede java objekty zpet na JSON)
    }

    @Override
    public LocalTime read(JsonReader in) throws IOException {
        in.beginObject();   //vstoupim do JSONu
        in.nextName();      //preskocime nazev klice (vteriny, hoidny...)
        var timeStr = in.nextString();  //precteme samotnou hodnotu casu jako text
        in.endObject();     //vystoupim ze slozenych zavorek
        return LocalTime.parse(timeStr);    //prevede text (08:00) na objekt LoaclTime


    }
}
