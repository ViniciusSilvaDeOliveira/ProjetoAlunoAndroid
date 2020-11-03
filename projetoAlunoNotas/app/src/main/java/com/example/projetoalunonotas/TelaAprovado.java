package com.example.projetoalunonotas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaAprovado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aprovado);

        final Spinner dropdown = findViewById(R.id.disciplina);
        Intent novoTexto = getIntent();
        Bundle parametros = novoTexto.getExtras();
        ArrayList<String> itens = parametros.getStringArrayList("nome");
        if(!itens.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itens);
            dropdown.setAdapter(adapter);
        }

        final EditText edtNotaA1 = (EditText) findViewById(R.id.edtNotaA1);
        final EditText edtNotaA2 = (EditText) findViewById(R.id.edtNotaA2);
        final TextView txtNotaFinal = (TextView) findViewById(R.id.txtNotaFinal);
        final TextView txtSituacao = (TextView) findViewById(R.id.txtSituacao);
        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")

            public void onClick(View v){

                if(edtNotaA1.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Informe uma nota", Toast.LENGTH_SHORT).show();
                } else if (edtNotaA2.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Informe uma nota", Toast.LENGTH_SHORT).show();
                }
                else {
                    Double notaA1 = Double.parseDouble(edtNotaA1.getText().toString());
                    Double notaA2 = Double.parseDouble(edtNotaA2.getText().toString());

                    if(notaA1 < 0 || notaA1 > 5) {
                        Toast.makeText(getApplicationContext(), "Digite uma nota entre 0 e 5", Toast.LENGTH_SHORT).show();
                    }else if(notaA2 < 0 || notaA2 > 5) {
                        Toast.makeText(getApplicationContext(), "Digite uma nota entre 0 e 5", Toast.LENGTH_SHORT).show();
                    } else {
                        Double notaFinal = notaA1 + notaA2;

                        Double notaMaior;

                        if(notaA1 > notaA2){
                            notaMaior = notaA1;
                        }else if(notaA2 > notaA1){
                            notaMaior = notaA2;
                        }else {
                            notaMaior = notaA1;
                        }

                        String disciplinaEscolhida = dropdown.getSelectedItem().toString();

                        txtNotaFinal.setText(notaFinal.toString());
                        if (notaFinal >= 6){
                            txtSituacao.setText("Aprovado");
                            txtSituacao.setTextColor(getColor(R.color.corAprovado));
                            btnTelaAprovadoFinal(disciplinaEscolhida, notaFinal, "Aprovado", notaMaior);
                        } else {
                            txtSituacao.setText("Você está de AF");
                            txtSituacao.setTextColor(getColor(R.color.corReprovado));
                            btnTelaAprovadoFinal(disciplinaEscolhida, notaFinal, "Reprovado", notaMaior);
                        }
                    }
                    //
                }
                //
            }
        });
    }

    public void btnTelaAprovadoFinal(String disciplina, Double nota, String situacao, Double notaMaior) {
        Intent novaActivity = new Intent(this, TelaAprovadoFinal.class);

        Bundle bundle = new Bundle();
        bundle.putString("disciplina", disciplina);
        bundle.putDouble("nota", nota);
        bundle.putString("situacao", situacao);
        bundle.putDouble("notaMaior", notaMaior);
        novaActivity.putExtras(bundle);

        startActivity(novaActivity);
    }
}