package com.lemzeeyyy.notebookapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.lemzeeyyy.notebookapplication.model.Note;
import com.lemzeeyyy.notebookapplication.model.NoteViewModel;

import java.sql.Timestamp;

public class UpdateNote extends AppCompatActivity {
    private EditText note_title;
    private EditText note_description;
    private long noteId = 0;
    private Button save_note_btn;
    private Boolean isUpdate = false;
    private Button update_note_btn;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        note_title = findViewById(R.id.edit_note_title);
        note_description = findViewById(R.id.note_descrip);
        save_note_btn = findViewById(R.id.save_button);
        update_note_btn = findViewById(R.id.update_button);
        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(UpdateNote.this
                .getApplication())
                .create(NoteViewModel.class);
        if (getIntent().hasExtra(MainActivity.NOTE_ID)) {
            noteId = getIntent().getLongExtra(MainActivity.NOTE_ID, 0);
            Log.d("noteId", "onCreate: "+noteId);
            noteViewModel.getNote(noteId).observe(this, note -> {
                if (note != null) {
                    note_title.setText(note.getNoteTitle());
                    note_description.setText(note.getNoteDescription());
                }
            });
            isUpdate = true;
        }

            save_note_btn.setOnClickListener(view -> {
                Intent replyIntent = getIntent();
                if (!TextUtils.isEmpty(note_title.getText())) {
                    String noteTitle = note_title.getText().toString();
                    String noteDescrip = note_description.getText().toString();
                    replyIntent.putExtra(MainActivity.NOTE_TITLE, noteTitle);
                    replyIntent.putExtra(MainActivity.NOTE_DESCRIPTION, noteDescrip);
                    setResult(RESULT_OK, replyIntent);
                } else {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                finish();
            });

            //setup update

            update_note_btn.setOnClickListener(view -> {
                long id = noteId;
                String noteTitle = note_title.getText().toString().trim();
                String noteDes = note_description.getText().toString().trim();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                if (TextUtils.isEmpty(noteDes) && TextUtils.isEmpty(noteDes)) {
                    Snackbar.make(note_description, R.string.empty, Snackbar.LENGTH_LONG)
                            .show();
                }else {
                    Note note = new Note();
                    note.setId(id);
                    note.setNoteTitle(noteTitle);
                    note.setNoteDescription(noteDes);
                    note.setTimestamp(timestamp);
                    NoteViewModel.updateNote(note);
                    finish();
                }
            });
            if(isUpdate)
                save_note_btn.setVisibility(View.GONE);
            else
                update_note_btn.setVisibility(View.GONE);
        }
}