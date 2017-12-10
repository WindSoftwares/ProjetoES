package com.mattheusbrito.projetoes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class telaQuiz extends AppCompatActivity {


    private Button botao1Resposta, botao2Resposta, botao3Resposta, botao4Resposta;
    private TextView perguntaTxt;
    private Set<String> teste = new HashSet<String>();

    //variavel para teste da tela
    private int cont = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);
        //teste sobre como pegar as respostas de uma pergunta
        teste.add("safadeza1");
        teste.add("safadeza2");
        teste.add("safadeza3");
        teste.add("safadeza4");

        carregaBotoes();
        carregaPrimeiraPergunta();



        botao1Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont++;
                //onde vamos verificar se a resposta do usuário está correta
                verificaResposta("botao",botao1Resposta);
                //mudo a cor do background e seto tudo depois de 1 s
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       /*
                       quando tivermas a questao, iremos passar a pergunta daquela questao
                       inPlay(questao.getDescription())
                        */
                        inPlay("pergunta botao1",botao1Resposta);
                    }
                }, 1000);

            }
        });
        botao2Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont++;
                verificaResposta("botao",botao2Resposta);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inPlay("pergunta botao2",botao2Resposta);

                    }
                }, 1000);
            }
        });

        botao3Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont++;
                verificaResposta("botao",botao3Resposta);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inPlay("pergunta botao3",botao3Resposta);
                    }
                }, 1000);

            }
        });

        botao4Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont++;
               verificaResposta("botao",botao4Resposta);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inPlay("pergunta botao4",botao4Resposta);
                    }
                }, 1000);

            }
        });
    }

    /**
     * metodo onde vamos mudar todas as respostas dos botoes, para que o usuario possa responder
     */
    public void mudaBotoes() {
        //criar um arrayList para pegar cada botao que sera usado
        List<String> list = new ArrayList<String>(teste);

        botao1Resposta.setText(list.get(0));
        botao2Resposta.setText(list.get(1));
        botao3Resposta.setText(list.get(2));
        botao4Resposta.setText(list.get(3));
    }

    /**
     * metodo em que vamos setar se o botao e "clicável" ou não
     *
     * @param enableOrNot
     */
    public void setClickButtons(boolean enableOrNot) {
        botao1Resposta.setClickable(enableOrNot);
        botao2Resposta.setClickable(enableOrNot);
        botao3Resposta.setClickable(enableOrNot);
        botao4Resposta.setClickable(enableOrNot);
    }

    /**
     * metodo que vamos usar para saber quando o quiz acabada(alternativa)
     *
     * @param jogadas
     * @return boolean
     */
    public boolean acabaQuiz(int jogadas) {
        if (jogadas == 10) {
            return true;
        }
        return false;
    }

    /**
     * metodo que vai fazer a tela aparecer jogavel depois de uma jogada
     * @param description
     */
    public void inPlay(String description,Button button) {
        if (acabaQuiz(cont)) {
            /*
             aqui ficaria a chamada da proxima tela, depois de responder todas as perguntas
            */
            finish();
        } else {
            mudaBotoes();
            /*seta a questao
             */
            perguntaTxt.setText(description);
            button.setBackgroundColor(Color.parseColor("#47476b"));
            setClickButtons(true);
        }

    }

    /**
     * metodo que carrega os botoes que serao usados na activity
     */
    public void carregaBotoes(){
        botao1Resposta = (Button) findViewById(R.id.resposta1Button);
        botao2Resposta = (Button) findViewById(R.id.resposta2Button);
        botao3Resposta = (Button) findViewById(R.id.resposta3Button);
        botao4Resposta = (Button) findViewById(R.id.resposta4Button);
        perguntaTxt = (TextView) findViewById(R.id.perguntaTxt);
    }

    /**
     * metodo que vai verificar a resposta de acordo com o botao
     * @param respostaCerta
     */
    public void verificaResposta(String respostaCerta,Button button){
        if (button.getText().equals(respostaCerta)) {
            button.setBackgroundColor(Color.parseColor("#33cc33"));
            //enable para não poder clicar em outros botoes
            setClickButtons(false);
        } else {
            button.setBackgroundColor(Color.parseColor("#cc0000"));
            //enable para não poder clicar em outros botoes
            setClickButtons(false);
        }
    }

    /**
     * metodo que vai mostrar na tela a primeira questão do quiz
     */
    public void carregaPrimeiraPergunta(){
        perguntaTxt.setText("primeira pergunta");
        //modo para ler um array To do
       // perguntaTxt.setText(Arrays.toString(list.toArray()));
    }
}
