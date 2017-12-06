package com.mattheusbrito.projetoes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class telaQuiz extends AppCompatActivity {


    private Button botao1Resposta,botao2Resposta,botao3Resposta,botao4Resposta;
    private TextView perguntaTxt;
    //variavel para teste da tela
    private int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);

        botao1Resposta = (Button)findViewById(R.id.resposta1Button);
        botao2Resposta = (Button)findViewById(R.id.resposta2Button);
        botao3Resposta = (Button)findViewById(R.id.resposta3Button);
        botao4Resposta = (Button)findViewById(R.id.resposta4Button);

        perguntaTxt = (TextView)findViewById(R.id.perguntaTxt);
        perguntaTxt.setText("Primeira pergunta");

        botao1Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                perguntaTxt.setText("Pergunta do botao1 ");
                cont++;
                //onde vamos verificar se a resposta do usuário está correta
                if(botao1Resposta.getText().equals("botao")){
                    botao1Resposta.setBackgroundColor(Color.parseColor("#33cc33"));
                    //enable para não poder clicar em outros botoes
                    setClickButtons(false);
                }else{
                    botao1Resposta.setBackgroundColor(Color.parseColor("#cc0000"));
                    //enable para não poder clicar em outros botoes
                    setClickButtons(false);
                }

                //mudo a cor do background e seto tudo depois de 2 s
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mudaBotoes();
                        perguntaTxt.setText("pergunta botao1");
                        botao1Resposta.setBackgroundColor(Color.parseColor("#f5f5f0"));
                        setClickButtons(true);
                        if(acabaQuiz(cont)){
                            /*
                            no nosso caso, teriamos o numero de jogadas do usuario e mudariamos para a tela da pontuacao
                             */
                            finish();
                        }
                    }
                },2000);

            }
        });
        botao2Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                if(botao2Resposta.getText().equals("botao")){
                    botao2Resposta.setBackgroundColor(Color.parseColor("#33cc33"));
                    setClickButtons(false);
                }else{
                    botao2Resposta.setBackgroundColor(Color.parseColor("#cc0000"));
                    setClickButtons(false);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mudaBotoes();
                        perguntaTxt.setText("Pergunta do botao2");
                        botao2Resposta.setBackgroundColor(Color.parseColor("#f5f5f0"));
                        setClickButtons(true);
                        if(acabaQuiz(cont)){
                            finish();
                        }
                    }
                },2000);
            }
        });

        botao3Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                perguntaTxt.setText("Pergunta do botao3");

                if(botao3Resposta.getText().equals("botao")){
                    botao3Resposta.setBackgroundColor(Color.parseColor("#33cc33"));
                    setClickButtons(false);
                }else{
                    botao3Resposta.setBackgroundColor(Color.parseColor("#cc0000"));
                    setClickButtons(false);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mudaBotoes();
                        perguntaTxt.setText("pergunta botao3");
                        botao3Resposta.setBackgroundColor(Color.parseColor("#f5f5f0"));
                        setClickButtons(true);
                        if(acabaQuiz(cont)){
                            finish();
                        }
                    }
                },2000);

            }
        });

        botao4Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                perguntaTxt.setText("Pergunta do botao4 ");

                if(botao4Resposta.getText().equals("botao")){
                    botao4Resposta.setBackgroundColor(Color.parseColor("#33cc33"));
                    setClickButtons(false);
                }else{
                    botao4Resposta.setBackgroundColor(Color.parseColor("#cc0000"));
                    setClickButtons(false);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mudaBotoes();
                        perguntaTxt.setText("pergunta botao4");
                        botao4Resposta.setBackgroundColor(Color.parseColor("#f5f5f0"));
                        setClickButtons(true);
                        if(acabaQuiz(cont)){
                            finish();
                        }
                    }
                },2000);

            }
        });
    }

    /**
     * metodo onde vamos mudar todas as respostas dos botoes, para que o usuario possa responder
     */
    public void mudaBotoes(){
        botao1Resposta.setText("outra resposta ");
        botao2Resposta.setText("outra resposta ");
        botao3Resposta.setText("outra resposta ");
        botao4Resposta.setText("outra resposta ");
    }

    /**
     * metodo em que vamos setar se o botao e "clicável" ou não
     * @param enableOrNot
     */
    public void setClickButtons(boolean enableOrNot){
        botao1Resposta.setClickable(enableOrNot);
        botao2Resposta.setClickable(enableOrNot);
        botao3Resposta.setClickable(enableOrNot);
        botao4Resposta.setClickable(enableOrNot);
    }

    /**
     * metodo que vamos usar para saber quando o quiz acabada(alternativa)
     * @return boolean
     * @param jogadas
     */
    public boolean acabaQuiz(int jogadas){
        if(jogadas == 10){
            return true;
        }
        return false;
    }
}
