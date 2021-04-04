package uz.devfest.projectfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RockMusicActivity extends AppCompatActivity {
    private List<Album> albums = new ArrayList();
    ListView listMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musics);
        //beginner initialization list
        setInitialData();
        //getting element ListView
        listMusic = findViewById(R.id.list_music);
        //creating adapter
        AlbumArrayAdapter stateAdapter = new AlbumArrayAdapter(this, R.layout.list_item_music, albums);
        //installing adapter
        listMusic.setAdapter(stateAdapter);
    }

    private void setInitialData() {
        albums.add(new Album("Sweet Emotion", "Aerosmith"));
        albums.add(new Album("Kashmir", "Led Zeppelin"));
        albums.add(new Album("Gimme Shelter", "Rolling Stones"));
        albums.add(new Album("A day in the Life", "The Beatles"));
        albums.add(new Album("Bohemian Rhapsody", "Queen"));
        albums.add(new Album("Everybody Wants Some!!", "Van Halen"));
        albums.add(new Album("Comfortably Numb", "Pink Floyd"));
        albums.add(new Album("Paranoid", "Black Sabbath"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}