package uz.devfest.projectfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class JazzMusicActivity extends AppCompatActivity {
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
        albums.add(new Album("So What", "Miles Davis"));
        albums.add(new Album("Fly Me To The Moon", "Frank Sinatra"));
        albums.add(new Album("Mood Indigo", "Duke Ellington & His Famous Orchestra"));
        albums.add(new Album("Take Five", "Dave Brubeck Quartet"));
        albums.add(new Album("The Girl From Ipanema", "Stan Getz & Joao Gilberto"));
        albums.add(new Album("Minnie The Moocher", "Cab Calloway"));
        albums.add(new Album("What A Wonderful World", "Louis Armstrong"));
        albums.add(new Album("Strange Fruit", "Billie Holiday"));
        albums.add(new Album("Georgia On My Mind", "Ray Charles"));
        albums.add(new Album("My Baby Just Cares For Me", "Nina Simone"));
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