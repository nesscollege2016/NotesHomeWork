package ness.tomerbu.edu.noteshomework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by android on 16/06/2016.
 */
public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    private Context context;
    private ArrayList<Note> notes;
    private LayoutInflater inflater;

    public NotesAdapter(Context context, ArrayList<Note> notes, LayoutInflater inflater) {
        this.context = context;
        this.notes = notes;
        this.inflater = inflater;
    }
    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.note_layout, parent, false);
        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note n = notes.get(position);
        holder.etNote.setText(n.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{
        EditText etNote;
        public NotesViewHolder(View itemView) {
            super(itemView);
            etNote = (EditText) itemView.findViewById(R.id.etNote);
        }
    }
}
