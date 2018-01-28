package com.windsoft.se.project.view;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.model.quiz.QuestioMock;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.season.Season;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    private String mCorrectAnswer;
    private Season mOwner;

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

    @SuppressLint("ResourceType")
    synchronized private void goToTheNextQuestion() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            QuizFragment fragment = new QuizFragment();
            fragment.setOwner(getOwner());
            getActivity().getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.mainFragment, fragment)
                    .commit();
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
        return response.equalsIgnoreCase(mCorrectAnswer);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        enableInteraction();
        ButterKnife.bind(this, view);
        bindQuiz();

        return view;
    }

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_quiz);
//        enableInteraction();
//
//        ButterKnife.bind(this);
//        bindQuiz();
//
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //Find way to get quiz from mock.
//    }

    private void bindQuiz() {
        if (QuestioMock.hasNext()) {
            Question question = mOwner.getNextQuestion();
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

    private void goToScoreScreen() {//TODO
        Intent scoreScreenIntent = new Intent();//TODO
        startActivity(scoreScreenIntent);
//        finish();
    }


    public void setOwner(Season owner) {
        mOwner = owner;
    }

    public Season getOwner() {
        return mOwner;
    }
}
