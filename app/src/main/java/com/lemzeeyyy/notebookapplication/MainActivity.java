package com.lemzeeyyy.notebookapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lemzeeyyy.notebookapplication.model.Note;
import com.lemzeeyyy.notebookapplication.model.NoteViewModel;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteViewModel = new ViewModelProvider
                .AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(NoteViewModel.class);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Note note = new Note("Network Analysis", "Kirchoffs Law",
                timestamp);
       //NoteViewModel.insertNote(note);
       // Log.d("TAG", "onCreate: "+note.getTimestamp());
        noteViewModel.getAllNotes().observe(this, notes -> {
            Log.d("TAG", "onCreate: "+notes);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}