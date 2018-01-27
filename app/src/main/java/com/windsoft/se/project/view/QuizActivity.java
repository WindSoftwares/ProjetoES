package com.windsoft.se.project.view;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.windsoft.se.project.util.Constant.ONE_SECOND;
import static com.windsoft.se.project.util.Constant.TWO_SECONDS;

public class QuizActivity extends AppCompatActivity {

    @BindView(R.id.question_text)
    TextView questionText;

    @BindView(R.id.firstAlternative_button)
    Button firstAlternativeButton;

    @BindView(R.id.secondAlternative_button)
    Button secondAlternativeButton;

    @BindView(R.id.thirdAlternative_button)
    Button thirdAlternativeButton;

    @BindView(R.id.fourthAlternative_button)
    Button fourthAlternativeButton;

    private String mCorrectResponse;

    @OnClick(R.id.firstAlternative_button)
    void firstAlternativeChosen() {
        checkButtonResponse(firstAlternativeButton);
    }

    @OnClick(R.id.secondAlternative_button)
    void secondAlternativeChosen() {
        checkButtonResponse(secondAlternativeButton);
    }

    @OnClick(R.id.thirdAlternative_button)
    void thirdAlternativeChosen() {
        checkButtonResponse(thirdAlternativeButton);
    }

    @OnClick(R.id.fourthAlternative_button)
    void fourthAlternativeChosen() {
        checkButtonResponse(fourthAlternativeButton);
    }

    private void checkButtonResponse(Button button) {
        disableInteraction();
        String response = fourthAlternativeButton.getText().toString();
        if (isCorrect(response)) {
            button.setBackgroundColor(Color.GREEN);
        }else {
            button.setBackgroundColor(Color.GREEN);
        }
        goToNextQuestion();
    }

    synchronized private void goToNextQuestion() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(getIntent());
            finish();
        }, TWO_SECONDS);
    }

    private void disableInteraction() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void enableInteraction() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private boolean isCorrect(String response) {
        return true;
//        return response.equalsIgnoreCase(mCorrectResponse);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        enableInteraction();

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Find way to get quiz from mock.
    }

}
