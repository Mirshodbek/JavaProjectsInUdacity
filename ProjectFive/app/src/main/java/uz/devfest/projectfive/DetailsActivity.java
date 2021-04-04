package uz.devfest.projectfive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL_ID = "pizzaId";
    private TextView nameText, addressText, openTime;
    private int name, address, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int imageId = (Integer) getIntent().getExtras().get(EXTRA_DETAIL_ID);
        int bukharaImage = Bukhara.bukhara[imageId].getImage();
        ImageFilterView imageFilterView = findViewById(R.id.image_detail);
        imageFilterView.setImageDrawable(ContextCompat.getDrawable(this, bukharaImage));
        name = Bukhara.bukhara[imageId].getDescription();
        nameText = findViewById(R.id.description_detail);
        nameText.setText(name);
        address = Bukhara.bukhara[imageId].getAddress();
        addressText = findViewById(R.id.find_your_way);
        addressText.setText(address);
        time = Bukhara.bukhara[imageId].getOpenTime();
        openTime = findViewById(R.id.timeInfo);
        openTime.setText(time);
    }
}