package com.lemzeeyyy.notebookapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lemzeeyyy.notebookapplication.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(Note note);

    @Query("DELETE FROM notebook_table")
    void deleteAllNote();

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("SELECT * FROM notebook_table")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notebook_table WHERE notebook_table.note_id==:id")
    LiveData<Note> getNote(long id);
}
