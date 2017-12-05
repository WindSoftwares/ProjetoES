package com.mattheusbrito.projetoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class telaQuiz extends AppCompatActivity {


    private Button botao1Resposta,botao2Resposta,botao3Resposta,botao4Resposta;
    private TextView perguntaTxt;
    private EditText perguntaEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);

        botao1Resposta = (Button)findViewById(R.id.resposta1Button);
        botao2Resposta = (Button)findViewById(R.id.resposta2Button);
        botao3Resposta = (Button)findViewById(R.id.resposta3Button);
        botao4Resposta = (Button)findViewById(R.id.resposta4Button);
        perguntaTxt = (TextView)findViewById(R.id.perguntaTxt);
        perguntaEditTxt = (EditText)findViewById(R.id.perguntaEditText);

        //teste para saber como mudar o EditTextView (que pode vir a ser a nossa pergunta);
        botao1Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perguntaEditTxt.setText("dasdad");
            }
        });
    }
}
