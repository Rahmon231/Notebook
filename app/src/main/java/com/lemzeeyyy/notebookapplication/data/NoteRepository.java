package com.lemzeeyyy.notebookapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lemzeeyyy.notebookapplication.model.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        this.noteDao = noteDao;
        this.allNotes = allNotes;
    }
}
