package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.windsoft.se.project.FacebookButtonClassTest;
import com.windsoft.se.project.R;
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
        publicar("20");
    }


    public void publicar(String pontuacao) {

        ShareDialog shareDialog = new ShareDialog(this);
        CallbackManager callbackManager = CallbackManager.Factory.create();

        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.i("Script", "Ok");
            }

            @Override
            public void onCancel() {
                Log.i("Script", "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("Script", e.getMessage());
            }
        });
        // caso a condição do if seja true, cria um content do tipo sharelink, que abre caixa de texto para usuario digitar
        // o que quer e publicar.
        // caso contrário abre um alert dialog dizendo que não consegue compartilhar

        //OBS ESCOLHER LINK E TROCAR PELO WWW.GOOGLE.COM ou pode trocar o endereço url por de uma foto.
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("www.google.com"))
                    .setContentDescription("Sua pontuação foi: " + pontuacao)
                    .build();
            shareDialog.show(linkContent);
        } else {
            new AlertDialog.Builder(getContext()).
                    setTitle("Error").
                    setMessage("Não conseguiu Compartilhar")
                    .show();
        }
    }


}
