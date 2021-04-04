package uz.devfest.projectfour;

public class Album {
    private String name;
    private int imageAlbumId;

    private String nameMusic;
    private String authorMusic;

    public static final Album[] album = {
            new Album("Rock", R.drawable.album_rock),
            new Album("Jazz", R.drawable.album_jazz),
            new Album("Pop", R.drawable.album_classica),
            new Album("Opera", R.drawable.album_opera)
    };

    public Album(String name, int imageAlbumId) {
        this.name = name;
        this.imageAlbumId = imageAlbumId;
    }

    public Album(String nameMusic, String authorMusic) {
        this.nameMusic = nameMusic;
        this.authorMusic = authorMusic;
    }

    public String getName() {
        return name;
    }

    public int getImageAlbumId() {
        return imageAlbumId;
    }

    public String getNameMusic() {
        return nameMusic;
    }

    public String getAuthorMusic() {
        return authorMusic;
    }
}
