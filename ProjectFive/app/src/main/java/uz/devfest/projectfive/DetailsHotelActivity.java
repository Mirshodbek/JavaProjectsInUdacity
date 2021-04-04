package uz.devfest.projectfive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsHotelActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL_HOTEL_ID = "pizzaId";
    private TextView nameText, addressText, openTime;
    private int name, address, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_hotel);

        int imageId = (Integer) getIntent().getExtras().get(EXTRA_DETAIL_HOTEL_ID);
        int hotel_image = Bukhara.hotels[imageId].getImage();
        ImageFilterView imageFilterView = findViewById(R.id.hotel_image);
        imageFilterView.setImageDrawable(ContextCompat.getDrawable(this, hotel_image));

        name = Bukhara.hotels[imageId].getDescription();
        nameText = findViewById(R.id.description_hotel);
        nameText.setText(name);
        address = Bukhara.hotels[imageId].getAddress();
        addressText = findViewById(R.id.find_your_way);
        addressText.setText(address);
        time = Bukhara.hotels[imageId].getOpenTime();
        openTime = findViewById(R.id.check_out);
        openTime.setText(time);
    }
}