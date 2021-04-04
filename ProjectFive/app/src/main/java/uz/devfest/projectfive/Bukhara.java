package uz.devfest.projectfive;

public class Bukhara {
    private int name;
    private int address;
    private int description;
    private int openTime;
    private int image;

    public static final Bukhara[] bukhara = {
            new Bukhara(R.string.ark, R.string.address_ark, R.string.description_ark, R.string.time, R.drawable.ark),
            new Bukhara(R.string.labi_hovuz, R.string.address_labi_hovuz, R.string.description_labi_hauz,
                    R.string._24_7, R.drawable.labi_hovuz),
            new Bukhara(R.string.minorai_kalon, R.string.address_minaret, R.string.description_minaret,
                    R.string._24_7, R.drawable.minorai_kalon)
    };

    public static final Bukhara[] hotels = {
            new Bukhara(R.string.zargaron, R.string.address_zargaron, R.string.description_zargaron,
                    R.string.zargaron_check_in, R.drawable.zargaron),
            new Bukhara(R.string.modarixon, R.string.address_modarixon, R.string.description_modarixon,
                    R.string.zargaron_check_in, R.drawable.modarixon),
            new Bukhara(R.string.minorai_kalon_hotel, R.string.address_minorai_kalon_hotel,
                    R.string.description_hotel_minorai_kalon, R.string.minorai_kalon_check_in, R.drawable.minorai_kalon_hotel)
    };

    public Bukhara(int name, int address, int description, int openTime, int image) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public int getAddress() {
        return address;
    }

    public int getDescription() {
        return description;
    }

    public int getOpenTime() {
        return openTime;
    }

    public int getImage() {
        return image;
    }
}
