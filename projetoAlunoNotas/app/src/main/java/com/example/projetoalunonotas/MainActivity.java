package com.example.projetoalunonotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner sistemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sistemas = (Spinner) findViewById(R.id.cursos);
        ArrayAdapter adapter = ArrayAdapter.createFromResource
                (this, R.array.listaDeCursos, android.R.layout.simple_spinner_item);
        sistemas.setAdapter(adapter);
    }

    public void btnDesistir(View v) {
        finish();
    }

    public void btnTelaDisciplina(View v) {
        Intent novaActivity = new Intent(this, TelaDisciplina.class);
        startActivity(novaActivity);
    }
}