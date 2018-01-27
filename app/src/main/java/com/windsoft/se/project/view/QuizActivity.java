package com.windsoft.se.project.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.model.QuestioMock;
import com.windsoft.se.project.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String mCorrectAnswer;

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
        String response = button.getText().toString();
        if (isCorrect(response)) {
            button.setBackgroundColor(Color.GREEN);
        }else {
            button.setBackgroundColor(Color.RED);
        }
        goToTheNextQuestion();
    }

    synchronized private void goToTheNextQuestion() {
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
        return response.equalsIgnoreCase(mCorrectAnswer);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        enableInteraction();

        ButterKnife.bind(this);
        bindQuiz();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Find way to get quiz from mock.
    }

    private void bindQuiz() {
        if (QuestioMock.hasNext()) {
            Question question = QuestioMock.getNext();
            question.shuffle();
            questionText.setText(question.getDescription());
            mCorrectAnswer = question.getCorrectAnswer();
            firstAlternativeButton.setText(question.pickAnswer());
            secondAlternativeButton.setText(question.pickAnswer());
            thirdAlternativeButton.setText(question.pickAnswer());
            fourthAlternativeButton.setText(question.pickAnswer());
        }else {
            goToScoreScreen();
        }
    }

    private void goToScoreScreen() {
        Intent scoreScreenIntent = new Intent();//TODO
        startActivity(scoreScreenIntent);
        finish();
    }


}
