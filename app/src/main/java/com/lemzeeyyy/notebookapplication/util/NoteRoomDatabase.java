package com.lemzeeyyy.notebookapplication.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lemzeeyyy.notebookapplication.data.NoteDao;
import com.lemzeeyyy.notebookapplication.model.Note;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public static final String NOTE_DATABASE = "note_database";
    public static volatile NoteRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NoteRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NoteRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class,NOTE_DATABASE)
                            .addCallback(sRoomDatabase)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final Callback sRoomDatabase = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                NoteDao noteDao = INSTANCE.noteDao();
                noteDao.deleteAllNote();
            });
        }
    };
    public abstract NoteDao noteDao();

}
