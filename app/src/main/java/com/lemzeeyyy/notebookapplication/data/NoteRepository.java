package com.lemzeeyyy.notebookapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lemzeeyyy.notebookapplication.model.Note;
import com.lemzeeyyy.notebookapplication.util.NoteRoomDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteRoomDatabase noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteRoomDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllData(){
        return allNotes;
    }

    public void insertTask(Note note){
        NoteRoomDatabase.databaseWriteExecutor.execute(()->{
            noteDao.insertNote(note);
        });
    }

    public void deleteAll(){
        NoteRoomDatabase.databaseWriteExecutor.execute(()->{
            noteDao.deleteAllNote();
        });
    }

    public void deleteTask(Note note){
        NoteRoomDatabase.databaseWriteExecutor.execute(()->{
            noteDao.deleteNote(note);
        });
    }

    public LiveData<Note> getTask(long id){
        return noteDao.getNote(id);
    }

    public void updateTask(Note note){
        NoteRoomDatabase.databaseWriteExecutor.execute(()->{
            noteDao.updateNote(note);
        });
    }
}
