package uz.devfest.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX_A = "indexA";
    private static final String KEY_INDEX_B = "indexB";
    private static final String KEY_SCORE_A = "scoreA";
    private static final String KEY_SCORE_B = "scoreB";
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    TextView scoreViewA, scoreViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt(KEY_INDEX_A, 0);
            scoreTeamB = savedInstanceState.getInt(KEY_INDEX_B, 0);
            scoreViewA = findViewById(R.id.team_a_score);
            CharSequence userScoreA = savedInstanceState.getCharSequence(KEY_SCORE_A);
            scoreViewA.setText(userScoreA);
            scoreViewB = findViewById(R.id.team_b_score);
            CharSequence userScoreB = savedInstanceState.getCharSequence(KEY_SCORE_B);
            scoreViewB.setText(userScoreB);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX_A, scoreTeamA);
        savedInstanceState.putInt(KEY_INDEX_B, scoreTeamB);
        scoreViewA = findViewById(R.id.team_a_score);
        CharSequence userScoreA = scoreViewA.getText();
        savedInstanceState.putCharSequence(KEY_SCORE_A, userScoreA);
        scoreViewB = findViewById(R.id.team_b_score);
        CharSequence userScoreB = scoreViewB.getText();
        savedInstanceState.putCharSequence(KEY_SCORE_B, userScoreB);
    }

    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 4;
        displayForTeamA(scoreTeamA);
    }

    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 6;
        displayForTeamA(scoreTeamA);
    }

    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 4;
        displayForTeamB(scoreTeamB);
    }

    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 6;
        displayForTeamB(scoreTeamB);
    }

    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    public void displayForTeamA(int score) {
        scoreViewA = findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        scoreViewB = findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(score));
    }
}