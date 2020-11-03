package com.example.projetoalunonotas;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaDisciplina extends ListActivity {

    private ArrayList<String> tarefas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_disciplina);

        atualizaListaTarefas();
        limpaTarefa();

        final Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edtTarefa = (EditText) findViewById(R.id.edtTarefa);
                String tarefa = edtTarefa.getText().toString();
                if ((tarefa != null) && (!tarefa.equals(""))) {
                    tarefas.add(edtTarefa.getText().toString());
                    limpaTarefa();
                    atualizaListaTarefas();
                }
            }
        });

    }

    private void limpaTarefa() {
        EditText edtTarefa = (EditText) findViewById(R.id.edtTarefa);
        edtTarefa.setText("");
        edtTarefa.requestFocus();
    }

    private void atualizaListaTarefas() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tarefas);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView lista, View v, int position, long id) {
        super.onListItemClick(lista, v, position, id);

        // Pegando o objeto da posição clicada
        Object obj = this.getListAdapter().getItem(position);

        // Pegando a string da objeto
        String elementoClicado = obj.toString();

        Toast.makeText(this, "Você clicou em: " + elementoClicado, Toast.LENGTH_SHORT).show();
    }

    public void btnTelaAprovado(View v) {
        Intent novaActivity = new Intent(this, TelaAprovado.class);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("nome", tarefas);
        novaActivity.putExtras(bundle);

        startActivity(novaActivity);
    }

    public void btnVoltar(View v) {
        Intent novaActivity = new Intent(this, MainActivity.class);
        startActivity(novaActivity);
    }

}