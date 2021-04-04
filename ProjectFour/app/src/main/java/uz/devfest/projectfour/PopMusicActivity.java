package uz.devfest.projectfour;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PopMusicActivity extends AppCompatActivity {
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
        albums.add(new Album("Wonder", "Shawn Mendes"));
        albums.add(new Album("Dynamite", "BTS"));
        albums.add(new Album("Holy", "Justin Bieber"));
        albums.add(new Album("Still Have Me", "Demi Lovato"));
        albums.add(new Album("Kings & Queens", "Ava Max"));
        albums.add(new Album("Midnight Sky", "Miley Cyrus"));
        albums.add(new Album("Watermelon Sugar", "Harry Styles"));
        albums.add(new Album("Be Like That", "Kane Brown, Swate Lee, Khalid"));
        albums.add(new Album("Ice Cream", "Selena Gomez"));
        albums.add(new Album("Lovesick Girls", "BLACKPINK"));
        albums.add(new Album("What a Feeling", "One Direction"));
        albums.add(new Album("Adore You", "Harry Styles"));
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
