package com.lemzeeyyy.notebookapplication.util;
import androidx.room.TypeConverter;

import java.sql.Timestamp;
import java.util.Date;

public class TimeConverter {

    @TypeConverter
    public static Timestamp toDate(Long timestamp) {
        return timestamp == null ? null : new Timestamp(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Timestamp time) {
        return time == null ? null : time.getTime();
    }
}
