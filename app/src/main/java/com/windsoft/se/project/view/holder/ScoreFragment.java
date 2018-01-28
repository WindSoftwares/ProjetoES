package com.windsoft.se.project.view.holder;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.util.Constant;
import com.windsoft.se.project.view.SeriesScreenFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GersonSales on 1/28/2018.
 */

public class ScoreFragment extends Fragment {


    @BindView(R.id.congrats_textView)
    TextView congratsText;

    @BindView(R.id.congratsMessage_textView)
    TextView congratsMessageText;


    @BindView(R.id.obtainedScore_textView)
    TextView obtainedScore;

    @BindView(R.id.targetScore_textView)
    TextView targetScore;

    @BindView(R.id.button)
    Button homeButton;

    @BindView(R.id.button2)
    Button nextQuizButton;

    @BindView(R.id.button3)
    Button shareScoreButton;

    private int mObtainedScore;
    private int mTargetScore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        ButterKnife.bind(this, view);

        obtainedScore.setText(String.valueOf(mObtainedScore));
        targetScore.setText(String.valueOf(mTargetScore));
        setCongratsText();

        return view;

    }

    private void setCongratsText() {
        if (mObtainedScore > mTargetScore / 2) {
            congratsText.setText(R.string.congratulations);
            congratsMessageText.setText(R.string.you_are_great);
        }else {
            congratsText.setText(R.string.fail_congrats);
            congratsMessageText.setText(R.string.you_know_anything);
        }
    }

    public void setObtainedScore(int obtainedScore) {
        this.mObtainedScore = obtainedScore;
    }

    public void setTargetScore(int targetScore) {
        this.mTargetScore = targetScore;
    }

    @SuppressLint("ResourceType")
    @OnClick(R.id.button)
    public void onClickHome() {
        getActivity()
                .getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.mainFragment, new SeriesScreenFragment())
                .commit();
    }


}
