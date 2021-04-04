package uz.devfest.projectone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private int mCurrentIndex = 0;
    private ImageButton left, right;
    private ImageFilterView image;

    private Picture[] mPictureBank = new Picture[]{
            new Picture(R.drawable.modarixon2),
            new Picture(R.drawable.modarixon3),
            new Picture(R.drawable.modarixon4),
            new Picture(R.drawable.modarixon5),
            new Picture(R.drawable.modarixon6),
            new Picture(R.drawable.modarixon7),
            new Picture(R.drawable.modarixon8),
            new Picture(R.drawable.modarixon9),
            new Picture(R.drawable.modarixon10)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.modarixon);

        left = findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0) {
                    return;
                } else {
                    mCurrentIndex = (mCurrentIndex - 1) % mPictureBank.length;
                    updatePicture();
                }
            }
        });

        right = findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mPictureBank.length;
                updatePicture();
            }
        });
    }

    private void updatePicture() {
        Drawable drawable = ContextCompat.getDrawable(this, mPictureBank[mCurrentIndex].getImage());
        image.setImageDrawable(drawable);
    }
}