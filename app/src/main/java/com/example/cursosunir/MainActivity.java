package com.example.cursosunir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private static final String Todos_campus = "Todos";

    private Spinner spinnerCampus;
    private RadioGroup radioGroupGrau;
    private CheckBox checkNoturno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        spinnerCampus = findViewById(R.id.spinner);
        radioGroupGrau = findViewById(R.id.radioGroup);
        checkNoturno = findViewById(R.id.checkBox);
        Button btnVerCursos = findViewById(R.id.button);

        configurarSpinner();
        radioGroupGrau.check(R.id.radioButton);

        btnVerCursos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaActivity.class);
            String campus = (String) spinnerCampus.getSelectedItem();
            if (campus != null && !campus.equals(Todos_campus)) {
                intent.putExtra("FiltroCampus", campus);
            }
            RadioButton marcado = findViewById(radioGroupGrau.getCheckedRadioButtonId());
            if (marcado != null && marcado.getId() != R.id.radioButton) {
                intent.putExtra("FiltroGrau", marcado.getText().toString());
            }
            intent.putExtra("FiltroNoturno", checkNoturno.isChecked());

            startActivity(intent);
        });
    }
    private void configurarSpinner() {
        TreeSet<String> campiUnicos = new TreeSet<>();
        for (Curso c : CursosData.getCursos()) {
            campiUnicos.add(c.getCampus());
        }

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add(Todos_campus);
        opcoes.addAll(campiUnicos);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, opcoes);
        spinnerCampus.setAdapter(adapter);
    }
}