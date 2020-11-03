package com.example.projetoalunonotas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaAprovadoFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aprovado_final);

        final TextView tvDisciplina = findViewById(R.id.txtNomeDisciplina);
        final TextView tvNota = findViewById(R.id.txtNota);
        final TextView tvSituacao = findViewById(R.id.txtAprovado);
        final EditText edtAF = (EditText) findViewById(R.id.edtAF);
        final Button btnCalcular = findViewById(R.id.btnCalcular);
        final TextView txtNotaFinal = (TextView) findViewById(R.id.txtNotaFinal);
        final TextView txtSituacao = (TextView) findViewById(R.id.txtSituacao);

        final Intent novoTexto = getIntent();
        final Bundle parametros = novoTexto.getExtras();
        String disciplina = parametros.getString("disciplina");
        final Double nota = parametros.getDouble("nota");
        //final Double notaAf = parametros.getDouble("edtAF");
        final Double notaMaior = parametros.getDouble("notaMaior");
        String situacao = parametros.getString("situacao");

        tvDisciplina.setText(disciplina);
        tvNota.setText(nota.toString());
        tvSituacao.setText(situacao);

        if (nota >= 6) {
            edtAF.setVisibility(View.GONE);
            btnCalcular.setVisibility(View.GONE);
        } else {
            edtAF.setVisibility(View.VISIBLE);
            btnCalcular.setVisibility(View.VISIBLE);
        }

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "SetTextI18n"})

            public void onClick(View v) {

                if(edtAF.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Informe uma nota", Toast.LENGTH_SHORT).show();
                } else {
                    Double notaAf = Double.parseDouble(edtAF.getText().toString());

                    if (notaAf < 0 || notaAf > 5 || notaAf == null) {
                        Toast.makeText(getApplicationContext(), "Digite uma nota entre 0 e 5", Toast.LENGTH_SHORT).show();
                    } else {
                        final Double notafinal = notaMaior + notaAf;

                        txtNotaFinal.setText(notafinal.toString());
                        if (notafinal >= 6) {
                            txtSituacao.setText("Aprovado");
                            txtSituacao.setTextColor(getColor(R.color.corAprovado));
                        } else {
                            txtSituacao.setText("Reprovado");
                            txtSituacao.setTextColor(getColor(R.color.corReprovado));
                        }
                    }
                }
            }
        });
    }

    public void btnTelaAprovado (View v){
        // matar a activy que ta no momento e voltar para a anterior
        super.onBackPressed();
    }
}