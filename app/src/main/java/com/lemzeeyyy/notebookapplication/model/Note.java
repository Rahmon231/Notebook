package com.lemzeeyyy.notebookapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lemzeeyyy.notebookapplication.util.TimeConverter;

import java.sql.Timestamp;
@Entity(tableName = "notebook_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    public long noteId;

    @ColumnInfo(name = "note_title")
    public String noteTitle;

    @ColumnInfo(name = "note_description")
    public String noteDescription;

    @ColumnInfo(name = "time_stamp")
    @TypeConverters(TimeConverter.class)
    public Timestamp timestamp;

    public Note(String noteTitle, String noteDescription, Timestamp timestamp) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.timestamp = timestamp;
    }

    public long getId() {
        return noteId;
    }

    public void setId(long noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
