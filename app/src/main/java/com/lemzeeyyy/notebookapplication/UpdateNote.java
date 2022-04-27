package com.lemzeeyyy.notebookapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lemzeeyyy.notebookapplication.model.NoteViewModel;

public class UpdateNote extends AppCompatActivity {
   private EditText note_title;
    private EditText note_description;
    private Button save_note;
    private Button update_note;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        note_title = findViewById(R.id.edit_note_title);
        note_description = findViewById(R.id.note_descrip);
        save_note = findViewById(R.id.save_button);
        update_note = findViewById(R.id.update_button);
        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(UpdateNote.this
                .getApplication())
                .create(NoteViewModel.class);

        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = getIntent();
                if (TextUtils.isEmpty(note_title.getText())) {
                    String noteTitle = note_title.getText().toString();
                    String noteDescrip = note_description.getText().toString();
                    replyIntent.putExtra(MainActivity.NOTE_TITLE, noteTitle);
                    replyIntent.putExtra(MainActivity.NOTE_DESCRIPTION, noteDescrip);
                    setResult(RESULT_OK, replyIntent);
                } else {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                finish();
            }
        });
    }
}