package uz.devfest.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private EditText editOne, editTwo;
    private RadioGroup radioGroup;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private CheckBox checkBoxOne, checkBoxTwo, checkBoxThree, checkBoxFour, checkBoxFive;
    private Button result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editOne();
        radio();
        check();
        result();
    }

    private void editOne() {
        editTwo = findViewById(R.id.editAnswerTwo);
        editOne = findViewById(R.id.editAnswer);
        editOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                s = editOne.getText().toString();
                if (((String) s).equalsIgnoreCase("Tomorrow")) {
                    score++;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                s = editTwo.getText().toString();
                if (((String) s).equalsIgnoreCase("Fire")) {
                    score++;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void radio() {
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonA = findViewById(R.id.radioOne);
        radioButtonB = findViewById(R.id.radioTwo);
        radioButtonC = findViewById(R.id.radioThree);
        radioButtonD = findViewById(R.id.radioFour);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioOne:
                        radioButtonC.setEnabled(false);
                        radioButtonB.setEnabled(false);
                        radioButtonD.setEnabled(false);
                        break;
                    case R.id.radioTwo:
                        radioButtonC.setEnabled(false);
                        radioButtonA.setEnabled(false);
                        radioButtonD.setEnabled(false);
                        break;
                    case R.id.radioThree:
                        radioButtonA.setEnabled(false);
                        radioButtonB.setEnabled(false);
                        radioButtonD.setEnabled(false);
                        score++;
                        break;
                    case R.id.radioFour:
                        radioButtonC.setEnabled(false);
                        radioButtonB.setEnabled(false);
                        radioButtonA.setEnabled(false);
                        break;
                }
            }
        });
    }

    private void check() {
        checkBoxOne = findViewById(R.id.checkOne);
        checkBoxTwo = findViewById(R.id.checkTwo);
        checkBoxThree = findViewById(R.id.checkThree);
        checkBoxFour = findViewById(R.id.checkFour);
        checkBoxFive = findViewById(R.id.checkFive);
        checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (checkBoxTwo.isChecked() | checkBoxFour.isChecked() | checkBoxFive.isChecked()) {
                            return;
                        } else {
                            score++;
                        }
                    }
                });
            }
        });

    }

    private void result() {
        editOne = findViewById(R.id.editAnswer);
        radioButtonA = findViewById(R.id.radioOne);
        radioButtonB = findViewById(R.id.radioTwo);
        radioButtonC = findViewById(R.id.radioThree);
        radioButtonD = findViewById(R.id.radioFour);
        checkBoxOne = findViewById(R.id.checkOne);
        checkBoxThree = findViewById(R.id.checkThree);
        editTwo = findViewById(R.id.editAnswerTwo);
        result = findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score > 0) {
                    editOne.setText("Tomorrow");
                    checkBoxOne.setTextColor(Color.GREEN);
                    checkBoxThree.setTextColor(Color.GREEN);
                    radioButtonC.setTextColor(Color.GREEN);
                    if (radioButtonA.isChecked()) {
                        radioButtonA.setTextColor(Color.RED);
                    }
                    if (radioButtonB.isChecked()) {
                        radioButtonB.setTextColor(Color.RED);
                    }
                    if (radioButtonD.isChecked()) {
                        radioButtonD.setTextColor(Color.RED);
                    }
                    editTwo.setText("Fire");
                    final String resultScore = Integer.toString(score);
                    Toast.makeText(MainActivity.this, "You found " + resultScore + " correct answer!",
                            Toast.LENGTH_LONG).show();
                    result.setEnabled(false);
                }
            }
        });
    }
}