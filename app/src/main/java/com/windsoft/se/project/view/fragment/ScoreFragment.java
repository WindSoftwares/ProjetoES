package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.windsoft.se.project.R;
import com.windsoft.se.project.util.MediaUtil;
import com.windsoft.se.project.util.StaticFlow;

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

    @BindView(R.id.nextQuiz_button)
    ImageButton nextQuizButton;

    @BindView(R.id.shareScore_button)
    ImageButton shareScoreButton;

    private int mObtainedScore;
    private int mTargetScore;

    public ScoreFragment() {
        mObtainedScore = StaticFlow.getActualQuiz().getGatheredScore();
        mTargetScore = StaticFlow.getActualQuiz().getTopScore();
    }

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
        if (mObtainedScore == mTargetScore) {
            congratsText.setText(R.string.congratulations);
            congratsMessageText.setText(R.string.you_are_the_best);
        }else if (mObtainedScore >= mTargetScore * 0.9) {
            congratsText.setText(R.string.congratulations);
            congratsMessageText.setText(R.string.almost_there);
        }else if (mObtainedScore >= mTargetScore * 0.7) {
            congratsText.setText(R.string.congratulations);
            congratsMessageText.setText(R.string.you_are_great);
        }else if (mObtainedScore >= mTargetScore * 0.5) {
            congratsText.setText(R.string.congratulations);
            congratsMessageText.setText(R.string.you_need_to_practice);
        }else if (mObtainedScore >= mTargetScore * 0.2) {
            congratsText.setText(R.string.fail_congrats);
            congratsMessageText.setText(R.string.you_are_rust);
        }else {
            congratsText.setText(R.string.fail_congrats);
            congratsMessageText.setText(R.string.you_know_anything);
        }
    }

    @SuppressLint("ResourceType")
    @OnClick(R.id.nextQuiz_button)
    public void onClickNextQuiz() {
        getActivity()
                .getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.mainFragment, new SeasonScreenFragment())
                .commit();
    }

    @OnClick(R.id.shareScore_button)
    public void onClickShareScore() {
        publishScore();
    }


    public void publishScore() {
        ShareDialog shareDialog = new ShareDialog(this);

        if (ShareDialog.canShow(SharePhotoContent.class)) {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(MediaUtil.getScreenShot(getView()))
                    .build();

            SharePhotoContent photoContent = new SharePhotoContent.Builder()
                    .addPhoto(sharePhoto)
                    .build();
            shareDialog.show(photoContent, ShareDialog.Mode.AUTOMATIC);
        } else {
            new AlertDialog.Builder(getContext()).
                    setTitle("Error").
                    setMessage("You need to have the Facebook App installed")
                    .show();
        }
    }
}
