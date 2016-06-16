package ness.tomerbu.edu.noteshomework;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNotes;
    ArrayList<Note> notes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getNotesFromDisk();
        //fetchNotes();
        NotesAdapter adapter = new NotesAdapter(this, notes, getLayoutInflater());
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
}
