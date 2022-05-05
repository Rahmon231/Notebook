package com.lemzeeyyy.notebookapplication.adapter;

import com.lemzeeyyy.notebookapplication.model.Note;

public interface OnNoteClickListener {
    void onNoteClick(int position, Note note);
    void deleteClick(int position, Note note);
    void shareClick(int position, Note note);
}
