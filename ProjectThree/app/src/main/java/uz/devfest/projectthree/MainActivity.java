package uz.devfest.projectthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private static final String KEY_INDEX_QUESTION = "question";
    private static final String KEY_INDEX_A = "indexA";
    private static final String KEY_INDEX_B = "indexB";
    private static final String KEY_INDEX_C = "indexC";
    private static final String KEY_INDEX_D = "indexD";
    private int mCurrentIndex = 0;
    private int mCurrentAnswer = 0;
    private int mCurrentCorrect = 0;
    private int score = 0;
    private int question, correct, answerOne, answerTwo, answerThree, answerFour;
    private TextView questionText;
    private TextView answerA, answerB, answerC, answerD;
    private Button next;
    private ConstraintLayout btnA, btnB, btnC, btnD;
    Vibrator vibrator;

    private Question[] questions = new Question[]{
            new Question(R.string.question, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, true),
            new Question(R.string.question4, true),
            new Question(R.string.question5, true),
            new Question(R.string.question6, true),
            new Question(R.string.question7, true),
            new Question(R.string.question8, true),
            new Question(R.string.question9, true),
            new Question(R.string.question10, true)
    };
    private Question[] answers = new Question[]{
            new Question(R.string.textA, R.string.textB, R.string.textC, R.string.textD),
            new Question(R.string.answerA2, R.string.answerB2, R.string.answerC2, R.string.answerD2),
            new Question(R.string.answerA3, R.string.answerB3, R.string.answerC3, R.string.answerD3),
            new Question(R.string.answerA4, R.string.answerB4, R.string.answerC4, R.string.answerD4),
            new Question(R.string.answerA5, R.string.answerB5, R.string.answerC5, R.string.answerD5),
            new Question(R.string.answerA6, R.string.answerB6, R.string.answerC6, R.string.answerD6),
            new Question(R.string.answerA7, R.string.answerB7, R.string.answerC7, R.string.answerD7),
            new Question(R.string.answerA8, R.string.answerB8, R.string.answerC8, R.string.answerD8),
            new Question(R.string.answerA9, R.string.answerB9, R.string.answerC9, R.string.answerD9),
            new Question(R.string.answerA10, R.string.answerB10, R.string.answerC10, R.string.answerD10)
    };

    private Question[] correctAnswer = new Question[]{
            new Question(R.string.textC),
            new Question(R.string.answerA2),
            new Question(R.string.answerC3),
            new Question(R.string.answerB4),
            new Question(R.string.answerC5),
            new Question(R.string.answerA6),
            new Question(R.string.answerC7),
            new Question(R.string.answerB8),
            new Question(R.string.answerC9),
            new Question(R.string.answerC10)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mCurrentAnswer = savedInstanceState.getInt(KEY_INDEX, 0);
            mCurrentCorrect = savedInstanceState.getInt(KEY_INDEX, 0);
            questionText = findViewById(R.id.question);
            CharSequence userQuestion = savedInstanceState.getCharSequence(KEY_INDEX_QUESTION);
            questionText.setText(userQuestion);
            answerA = findViewById(R.id.textA);
            CharSequence userAnswerA = savedInstanceState.getCharSequence(KEY_INDEX_A);
            answerA.setText(userAnswerA);
            answerB = findViewById(R.id.textB);
            CharSequence userAnswerB = savedInstanceState.getCharSequence(KEY_INDEX_B);
            answerB.setText(userAnswerB);
            answerC = findViewById(R.id.textC);
            CharSequence userAnswerC = savedInstanceState.getCharSequence(KEY_INDEX_C);
            answerC.setText(userAnswerC);
            answerD = findViewById(R.id.textD);
            CharSequence userAnswerD = savedInstanceState.getCharSequence(KEY_INDEX_D);
            answerD.setText(userAnswerD);

        }

        next();
        btnA();
        btnB();
        btnC();
        btnD();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(KEY_INDEX, mCurrentAnswer);
        savedInstanceState.putInt(KEY_INDEX, mCurrentCorrect);
        questionText = findViewById(R.id.question);
        question = questions[mCurrentIndex].getQuestion();
        questionText.setText(question);
        CharSequence userQuestion = questionText.getText();
        savedInstanceState.putCharSequence(KEY_INDEX_QUESTION, userQuestion);
        answerA = findViewById(R.id.textA);
        answerOne = answers[mCurrentAnswer].getAnswerA();
        answerA.setText(answerOne);
        CharSequence userAnswerA = answerA.getText();
        savedInstanceState.putCharSequence(KEY_INDEX_A, userAnswerA);
        answerB = findViewById(R.id.textB);
        answerTwo = answers[mCurrentAnswer].getAnswerB();
        answerB.setText(answerTwo);
        CharSequence userAnswerB = answerB.getText();
        savedInstanceState.putCharSequence(KEY_INDEX_B, userAnswerB);
        answerC = findViewById(R.id.textC);
        answerThree = answers[mCurrentAnswer].getAnswerC();
        answerC.setText(answerThree);
        CharSequence userAnswerC = answerC.getText();
        savedInstanceState.putCharSequence(KEY_INDEX_C, userAnswerC);
        answerD = findViewById(R.id.textD);
        answerFour = answers[mCurrentAnswer].getAnswerD();
        answerD.setText(answerFour);
        CharSequence userAnswerD = answerD.getText();
        savedInstanceState.putCharSequence(KEY_INDEX_D, userAnswerD);
    }

    public void btnA() {
        btnA = findViewById(R.id.constraintA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerOne == correct) {
                    score++;
                    btnA.setBackgroundResource(R.drawable.shape_correct);

                } else {
                    btnA.setBackgroundResource(R.drawable.shape_incorrect);
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                }
                btnD.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
            }
        });
    }

    public void btnB() {
        btnB = findViewById(R.id.constraintB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerTwo == correct) {
                    score++;
                    btnB.setBackgroundResource(R.drawable.shape_correct);

                } else {
                    btnB.setBackgroundResource(R.drawable.shape_incorrect);
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                }
                btnA.setEnabled(false);
                btnD.setEnabled(false);
                btnC.setEnabled(false);
            }
        });
    }

    public void btnC() {
        btnC = findViewById(R.id.constraintC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerThree == correct) {
                    score++;
                    btnC.setBackgroundResource(R.drawable.shape_correct);
                } else {
                    btnC.setBackgroundResource(R.drawable.shape_incorrect);
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                }
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnD.setEnabled(false);
            }
        });
    }

    public void btnD() {
        btnD = findViewById(R.id.constraintD);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerFour == correct) {
                    score++;
                    btnD.setBackgroundResource(R.drawable.shape_correct);
                } else {
                    btnD.setBackgroundResource(R.drawable.shape_incorrect);
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                }
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
            }
        });
    }

    public void next() {
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex >= 9) {
                    String scoreToast = Integer.toString(score);
                    Toast.makeText(MainActivity.this, "You found: " + scoreToast + " correct answer!",
                            Toast.LENGTH_LONG).show();
                }
                setUpdate();
            }
        });
    }

    public void setUpdate() {
        mCurrentIndex = (mCurrentIndex + 1) % questions.length;
        mCurrentAnswer = (mCurrentAnswer + 1) % answers.length;
        mCurrentCorrect = (mCurrentCorrect + 1) % correctAnswer.length;
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
        btnA.setBackgroundResource(R.drawable.shape);
        btnB.setBackgroundResource(R.drawable.shape);
        btnC.setBackgroundResource(R.drawable.shape);
        btnD.setBackgroundResource(R.drawable.shape);
        updateQuestion();

    }

    private void updateQuestion() {
        questionText = findViewById(R.id.question);
        answerA = findViewById(R.id.textA);
        answerB = findViewById(R.id.textB);
        answerC = findViewById(R.id.textC);
        answerD = findViewById(R.id.textD);
        question = questions[mCurrentIndex].getQuestion();
        answerOne = answers[mCurrentAnswer].getAnswerA();
        answerTwo = answers[mCurrentAnswer].getAnswerB();
        answerThree = answers[mCurrentAnswer].getAnswerC();
        answerFour = answers[mCurrentAnswer].getAnswerD();
        correct = correctAnswer[mCurrentCorrect].getCorrectAnswer();
        questionText.setText(question);
        answerA.setText(answerOne);
        answerB.setText(answerTwo);
        answerC.setText(answerThree);
        answerD.setText(answerFour);
    }
}