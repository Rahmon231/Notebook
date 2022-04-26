package com.lemzeeyyy.notebookapplication.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lemzeeyyy.notebookapplication.data.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    public static NoteRepository repository;
    public final LiveData<List<Note>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllData();
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
