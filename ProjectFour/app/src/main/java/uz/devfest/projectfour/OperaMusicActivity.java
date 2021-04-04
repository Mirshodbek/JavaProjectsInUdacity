package uz.devfest.projectfour;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OperaMusicActivity extends AppCompatActivity {
    private List<Album> albums = new ArrayList();
    ListView listMusic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        albums.add(new Album("Eine kleine Nachtmusik", "Mozart"));
        albums.add(new Album("Fur Elise", "Beethoven"));
        albums.add(new Album("The Four Seasons", "Vivaldi"));
        albums.add(new Album("Carmen", "Bizet"));
        albums.add(new Album("Bolero", "Ravel"));
        albums.add(new Album("Nessum Dorma", "Puccini"));
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
