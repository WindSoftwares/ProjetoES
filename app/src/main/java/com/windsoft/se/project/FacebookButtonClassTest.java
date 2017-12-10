package com.windsoft.se.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mattheusbrito.projetoes.R;
import com.facebook.FacebookDialog;


/**
 * Created by igor brasileiro on 01/12/2017.
 */

public class FacebookButtonClassTest extends FragmentActivity {

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
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

        Button btn = findViewById(R.id.testButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pontuacao = "10";
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentDescription("Sua pontuação foi: " + pontuacao)
                            .build();
                    shareDialog.show(linkContent);
                }
            }
        });
    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void publicar(String pontuacao) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentDescription("Sua pontuação foi: " + pontuacao)
                    .build();
            shareDialog.show(linkContent);
        } else {
            new AlertDialog.Builder(this).
                    setTitle("Error").
                    setMessage("Não conseguiu Compartilhar")
                    .show();
        }
    }
}