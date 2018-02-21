package com.windsoft.se.project;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


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

        // criar botão, fazndo referencia ao "botao teste login"
        Button btn = findViewById(R.id.testButton);

        // add observer no botão
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pontuacao = "10";
                publicar(pontuacao);
            }
        });
    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void publicar(String pontuacao) {
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
            new AlertDialog.Builder(this).
                    setTitle("Error").
                    setMessage("Não conseguiu Compartilhar")
                    .show();
        }
    }
}