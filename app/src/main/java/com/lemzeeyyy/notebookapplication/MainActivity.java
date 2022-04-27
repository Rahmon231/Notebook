package com.lemzeeyyy.notebookapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lemzeeyyy.notebookapplication.adapter.RecyclerViewAdapter;
import com.lemzeeyyy.notebookapplication.model.Note;
import com.lemzeeyyy.notebookapplication.model.NoteViewModel;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel = new ViewModelProvider
                .AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(NoteViewModel.class);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Note note = new Note("Network Analysis", "Kirchoffs Law",
//                timestamp);
//       NoteViewModel.insertNote(note);
//        Log.d("TAG", "onCreate: "+note.getTimestamp());

        noteViewModel.getAllNotes().observe(this, notes -> {
            // Log.d("TAG", "onCreate: "+notes);
            viewAdapter = new RecyclerViewAdapter(notes);
            recyclerView.setAdapter(viewAdapter);
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