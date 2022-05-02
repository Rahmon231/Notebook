package com.lemzeeyyy.notebookapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lemzeeyyy.notebookapplication.adapter.OnNoteClickListener;
import com.lemzeeyyy.notebookapplication.adapter.RecyclerViewAdapter;
import com.lemzeeyyy.notebookapplication.model.Note;
import com.lemzeeyyy.notebookapplication.model.NoteViewModel;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity implements OnNoteClickListener {
    public static final String NOTE_TITLE = "note_title";
    public static final String NOTE_DESCRIPTION = "note_description";
    private static final int START_ACTIVITY_REQUEST_CODE = 1;
    public static final String NOTE_ID = "note_id";
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter viewAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteViewModel = new ViewModelProvider
                .AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notes -> {
            // Log.d("TAG", "onCreate: "+notes);
            viewAdapter = new RecyclerViewAdapter(notes,this);
            recyclerView.setAdapter(viewAdapter);
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpdateNote.class);
                startActivityForResult(intent,START_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == START_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String noteTitle = data.getStringExtra(NOTE_TITLE);
            String noteDescr = data.getStringExtra(NOTE_DESCRIPTION);
            Note note = new Note(noteTitle,noteDescr,timestamp);
            NoteViewModel.insertNote(note);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Snackbar.make(recyclerView,R.string.about,Snackbar.LENGTH_LONG)
                .show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteClick(int position, Note note) {
        position = (int) note.getId();
        Log.d("position", "onNoteClick: "+position);
        Intent intent = new Intent(MainActivity.this, UpdateNote.class);
        intent.putExtra(NOTE_ID,note.getId());
        Log.d("TAGNote", "onNoteClick: "+position);
        startActivity(intent);
    }

    @Override
    public void deleteClick(int position, Note note) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage("Do you want to delete this note?")
                .setPositiveButton("Delete", (dialogInterface, i) -> NoteViewModel.deleteNote(note))
                .setNegativeButton("Cancel",null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}