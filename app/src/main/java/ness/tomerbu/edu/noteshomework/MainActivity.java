package ness.tomerbu.edu.noteshomework;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab;
    RecyclerView rvNotes;
    ArrayList<Note> notes = new ArrayList<>();
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getNotesFromDisk();
        //fetchNotes();
        adapter = new NotesAdapter(this, notes, getLayoutInflater());
        rvNotes = (RecyclerView) findViewById(R.id.rvNotes);
        rvNotes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvNotes.setAdapter(adapter);


    }

    private void getNotesFromDisk(){
        SharedPreferences prefs = getSharedPreferences("Notes", MODE_PRIVATE);
        int noteCount = prefs.getInt("NoteCount", 0);
        for (int i = 0; i < noteCount; i++) {
            String content = prefs.getString("note" + i, "");
            Note note = new Note("Title", content);
            notes.add(note);
        }
    }

    private void addNoteToDisk(Note note){
        SharedPreferences prefs = getSharedPreferences("Notes", MODE_PRIVATE);

        int noteCount = prefs.getInt("NoteCount", 0);

        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("note" + noteCount, note.getContent());
        edit.putInt("NoteCount", noteCount + 1);

        edit.commit();

    }

    private void fetchNotes() {
        String lorem = getString(R.string.lorem);
        Random r = new Random();


        for (int i = 0; i < 30; i++) {
            int rand = r.nextInt(lorem.length());
            String cut = lorem.substring(0, rand);
            Note note = new Note("Title", cut);
            addNoteToDisk(note);
            notes.add(note);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Fab clicked:
    @Override
    public void onClick(View view) {

        final SharedPreferences prefs = getSharedPreferences("Notes", MODE_PRIVATE);
        int noteCount = prefs.getInt("NoteCount", 0);
        final String noteName = "note" + noteCount;
        final SharedPreferences.Editor edit = prefs.edit();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.note_layout, (ViewGroup) findViewById(R.id.layout), false);
        final EditText etNote = (EditText) v.findViewById(R.id.etNote);

        etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit.putString(noteName, etNote.getText().toString());
                edit.apply();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        builder.setView(v);


        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(MainActivity.this, etNote.getText().toString(), Toast.LENGTH_SHORT).show();
                adapter.addNote(new Note("title",etNote.getText().toString()));
            }
        });
        builder.show();
        edit.putInt("NoteCount", noteCount+1);
        edit.commit();
    }
}
