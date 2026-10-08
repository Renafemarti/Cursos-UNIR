package com.example.cursosunir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements Adapter.OnItemClickListener {

    private ArrayList<Curso> lista;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        lista = CursosData.getCursos();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(lista, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Curso f = lista.get(position);

        Intent intent = new Intent(ListaActivity.this, activityDetalhes.class);
        intent.putExtra("Nome", f.getNome());
        intent.putExtra("Campus", f.getCampus());
        intent.putExtra("Grau", f.getGrau());
        intent.putExtra("Turno", f.getTurno());
        intent.putExtra("Descricao", f.getDescricao());
        intent.putExtra("Site", f.getSite());
        intent.putExtra("imagem", f.getImagem());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {

    }

}