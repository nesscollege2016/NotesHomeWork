package ness.tomerbu.edu.noteshomework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return null;
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{

        public NotesViewHolder(View itemView) {
            super(itemView);
        }
    }
}
