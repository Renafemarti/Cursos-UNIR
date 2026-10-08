package com.example.cursosunir;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class activityDetalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        String nome = getIntent().getStringExtra("Nome");
        String campus = getIntent().getStringExtra("Campus");
        String grau = getIntent().getStringExtra("Grau");
        String turno = getIntent().getStringExtra("Turno");
        String descricao = getIntent().getStringExtra("Descricao");
        String site =  getIntent().getStringExtra("Site");
        String imagem = getIntent().getStringExtra("Imagem");

        ImageView imgDetalhe = findViewById(R.id.imgDetalhe);
        TextView txtNomeCurso = findViewById(R.id.txtNomeCurso);
        TextView txtGrau = findViewById(R.id.txtGrau);
        TextView txtCampus = findViewById(R.id.txtCampus);
        TextView txtTurno = findViewById(R.id.txtTurno);
        TextView txtDescricao = findViewById(R.id.txtDescricaoDetalhe);
        Button btnSite = findViewById(R.id.button3);
        Button btnCompartilhar = findViewById(R.id.button2);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        txtNomeCurso.setText(nome);
        txtGrau.setText(grau);
        txtCampus.setText(campus);
        txtTurno.setText(turno);
        txtDescricao.setText(descricao);

        Glide.with(this)
                .load(imagem)
                .into(imgDetalhe);


        btnVoltar.setOnClickListener(v -> finish());

        btnSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirSite();
            }

            private void AbrirSite() {
                Intent intentAbriSite = new Intent(Intent.ACTION_VIEW);
                intentAbriSite.setData(Uri.parse(site));
                startActivity(intentAbriSite);
            }
        });
        btnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartilharResultado();
            }

            private void compartilharResultado() {
                String mensagem = String.format(Locale.getDefault(),
                        "Informações do curso\n\n" +
                                "Nome: %s\n" +
                                "Campus: %s\n" +
                                "%s\n",
                        nome, campus, site);

                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);
                intentCompartilhar.setType("text/plain");
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, mensagem);
                startActivity(Intent.createChooser(intentCompartilhar, "Compartilhar informações via"));
            }
        });
    }

}