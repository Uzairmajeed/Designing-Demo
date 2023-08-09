package com.facebook.desiging_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textCancel;
    private TextView textBeforeYouContinue;
    private TextView textSolveMathProblem;
    private TextView textMathProblem;
    private EditText editAnswer;
    private TextView textafterclick;
    private ImageButton imageButton;
    private TextView textNewProblem;
    private int wrongAttempts = 0;
    private long tryAgainTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCancel = findViewById(R.id.textCancel);
        textBeforeYouContinue = findViewById(R.id.textBeforeYouContinue);
        textSolveMathProblem = findViewById(R.id.textSolveMathProblem);
        textMathProblem = findViewById(R.id.textMathProblem);
        editAnswer = findViewById(R.id.editAnswer);
        textafterclick = findViewById(R.id.textafterclick);
        imageButton = findViewById(R.id.imageButton);
        textNewProblem = findViewById(R.id.textNewProblem);

        editAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String answerText = editable.toString();

                // Check if the user has attempted wrong more than 5 times
                if (wrongAttempts >= 5) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime < tryAgainTime) {
                        // Show the countdown message
                        long remainingTime = (tryAgainTime - currentTime) / 1000; // Convert to seconds
                        textafterclick.setText("Too many Tries.\nTry again in 5 Min .");
                        textafterclick.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.yellow)); // Set the text color to red
                        imageButton.setImageResource(R.drawable.baseline_sentiment_very_dissatisfied_24); // Set your new image resource
                        return;
                    } else {
                        // Reset wrong attempts and tryAgainTime
                        wrongAttempts = 0;
                        tryAgainTime = 0;
                        textafterclick.setText(""); // Reset the text
                        textafterclick.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white)); // Reset the text color
                        imageButton.setImageResource(R.drawable.baseline_loop_24); // Reset to the original image
                    }
                }

                if (answerText.isEmpty()) {
                    textafterclick.setText(""); // Reset the text
                    textafterclick.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white)); // Reset the text color
                    imageButton.setImageResource(R.drawable.baseline_loop_24); // Reset to the original image
                } else if ("4".equals(answerText)) {
                    textafterclick.setText("Congratulations You Are in !");
                    textafterclick.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green)); // Set the text color to green
                    imageButton.setImageResource(R.drawable.baseline_check_24); // Set your new image resource
                } else {
                    wrongAttempts++;
                    if (wrongAttempts >= 5) {
                        tryAgainTime = System.currentTimeMillis() + 300000; // 5 minutes in milliseconds
                    }
                    textafterclick.setText("Wrong answer. Try again.");
                    textafterclick.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red)); // Set the text color to red
                    imageButton.setImageResource(R.drawable.baseline_loop_24); // Reset to the original image
                }
            }

        });

    }
}