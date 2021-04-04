package uz.devfest.projectfour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.List;

public class AlbumArrayAdapter extends ArrayAdapter<Album> {
    private LayoutInflater inflater;
    private int layout;
    private List<Album> stateOne;
    private boolean clickButton = false;

    public AlbumArrayAdapter(Context context, int resource, List<Album> stateOne) {
        super(context, resource, stateOne);
        this.stateOne = stateOne;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Album state = stateOne.get(position);

        viewHolder.nameMusic.setText(state.getNameMusic());
        viewHolder.nameAuthor.setText(state.getAuthorMusic());
        viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickButton) {
                    viewHolder.playButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.play));
                    viewHolder.playText.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Stop music", Toast.LENGTH_SHORT).show();
                    if (viewHolder.playButton.isClickable()) {
                        clickButton = false;
                    }
                } else {
                    viewHolder.playButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.pause_icon));
                    viewHolder.playText.setText("Pause music");
                    Toast.makeText(getContext(), "Pause music", Toast.LENGTH_SHORT).show();
                    if (viewHolder.playButton.isClickable()) {
                        clickButton = true;
                    }
                }
            }
        });
        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.playButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.play_icon));
                viewHolder.playText.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Playing music", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ConstraintLayout constraintLayout;
        final TextView nameMusic, nameAuthor, playText;
        ImageButton playButton;


        ViewHolder(View view) {
            constraintLayout = view.findViewById(R.id.constraint_music);
            nameMusic = view.findViewById(R.id.music_name);
            nameAuthor = view.findViewById(R.id.music_author);
            playText = view.findViewById(R.id.now_play);
            playButton = view.findViewById(R.id.play_button);
        }
    }
}