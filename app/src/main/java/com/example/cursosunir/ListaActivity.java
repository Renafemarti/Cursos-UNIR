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
        String filtroCampus = getIntent().getStringExtra("FiltroCampus");
        String filtroGrau = getIntent().getStringExtra("FiltroGrau");
        boolean somenteNoturno = getIntent().getBooleanExtra("FiltroNoturno", false);

        lista = new ArrayList<>();
        for (Curso c : CursosData.getCursos()) {
            if (filtroCampus != null && !c.getCampus().equals(filtroCampus)) continue;
            if (filtroGrau != null && !c.getGrau().equals(filtroGrau)) continue;
            if (somenteNoturno && !c.getTurno().equals("Noturno")) continue;
            lista.add(c);
        }

        if (lista.isEmpty()) {
            Toast.makeText(this, "Nenhum curso encontrado com esses filtros.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

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
        intent.putExtra("Imagem", f.getImagem());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {

    }

}