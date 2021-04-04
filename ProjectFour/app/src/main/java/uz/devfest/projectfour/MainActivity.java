package uz.devfest.projectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_album);

        String[] albumName = new String[Album.album.length];
        for (int i = 0; i < albumName.length; i++) {
            albumName[i] = Album.album[i].getName();
        }
        int[] albumImage = new int[Album.album.length];
        for (int i = 0; i < albumImage.length; i++) {
            albumImage[i] = Album.album[i].getImageAlbumId();
        }
        CaptionImageAdapter adapter = new CaptionImageAdapter(albumName, albumImage);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionImageAdapter.Listener() {
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        Intent rockIntent = new Intent(MainActivity.this, RockMusicActivity.class);
                        startActivity(rockIntent);
                        break;
                    case 1:
                        Intent jazzIntent = new Intent(MainActivity.this, JazzMusicActivity.class);
                        startActivity(jazzIntent);
                        break;
                    case 2:
                        Intent popIntent = new Intent(MainActivity.this, PopMusicActivity.class);
                        startActivity(popIntent);
                        break;
                    case 3:
                        Intent operaIntent = new Intent(MainActivity.this, OperaMusicActivity.class);
                        startActivity(operaIntent);
                        break;
                }
            }
        });
    }
}