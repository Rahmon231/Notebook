package com.lemzeeyyy.notebookapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lemzeeyyy.notebookapplication.R;
import com.lemzeeyyy.notebookapplication.model.Note;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private final List<Note> noteList;
    private final OnNoteClickListener onNoteClickListener;

    public RecyclerViewAdapter(List<Note> noteList, OnNoteClickListener onNoteClickListener) {
        this.noteList = noteList;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.note_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    holder.note_title.setText(noteList.get(position).noteTitle);
    holder.time_stamp.setText("Last updated: "+noteList.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView note_title;
        private TextView time_stamp;
        OnNoteClickListener noteClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title = itemView.findViewById(R.id.row_note_title);
            time_stamp = itemView.findViewById(R.id.row_time_stamp);
            this.noteClickListener = onNoteClickListener;
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            Note currentNote = noteList.get(getAdapterPosition());
            int id = view.getId();
            if(id == R.id.note_row){
                onNoteClickListener.onNoteClick(getAdapterPosition(),currentNote);
            }

        }
    }
}
