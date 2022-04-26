package com.lemzeeyyy.notebookapplication.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lemzeeyyy.notebookapplication.data.NoteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class NoteRoomDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public static final String NOTE_DATABASE = "note_database";
    public static volatile NoteRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static final RoomDatabase.Callback sRoomDatabase = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                NoteDao noteDao = INSTANCE.noteDao();
                noteDao.deleteAllNote();
            });
        }
    };
    public static NoteRoomDatabase getDatabase(Context context){
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

    public abstract NoteDao noteDao();

}
