package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.quiz.Quiz;
import com.windsoft.se.project.util.StaticFlow;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.windsoft.se.project.util.Constant.FIVE_SECONDS;
import static com.windsoft.se.project.util.Constant.TWO_SECONDS;

public class QuizFragment extends Fragment {

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


    private Answer mCorrectAnswer;
//    private static Season mOwner;
    private static int mGatheredScore;
    private Question mActualQuestion;
    private Handler handler = new Handler();

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
            StaticFlow.getActualQuiz().increaseScoreByOne();
            mActualQuestion.setCorrectAnswered(true);
        }else {
            button.setBackgroundColor(Color.RED);
        }
        goToTheNextQuestion();
    }

    @SuppressLint("ResourceType")
    synchronized private void goToTheNextQuestion() {
        handler.postDelayed(() -> {
                getActivity().getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.mainFragment, new QuizFragment())
                        .commitAllowingStateLoss();
        }, TWO_SECONDS);

    }


    private void disableInteraction() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void enableInteraction() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private boolean isCorrect(String response) {
        return response.equalsIgnoreCase(mCorrectAnswer.getText());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (!(savedInstanceState == null)) return getView();

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        enableInteraction();
        ButterKnife.bind(this, view);
        bindQuiz();

        return view;
    }

    private void bindQuiz() {
        if (StaticFlow.getActualQuiz().hasNext()) {
            mActualQuestion = StaticFlow.getActualQuiz().getNextQuestion();
            mActualQuestion.shuffle();
            questionText.setText(mActualQuestion.getDescription());
            mCorrectAnswer = mActualQuestion.getCorrectAnswer();
            firstAlternativeButton.setText(mActualQuestion.pickAnswer());
            secondAlternativeButton.setText(mActualQuestion.pickAnswer());
            thirdAlternativeButton.setText(mActualQuestion.pickAnswer());
            fourthAlternativeButton.setText(mActualQuestion.pickAnswer());
        }else {
            goToScoreScreen();
        }
    }

    @SuppressLint("ResourceType")
    private void goToScoreScreen() {
        getActivity()
                .getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.mainFragment, new ScoreFragment())
                .commitAllowingStateLoss();
    }





}
