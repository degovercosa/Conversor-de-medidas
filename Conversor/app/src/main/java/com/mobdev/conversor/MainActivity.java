package com.mobdev.conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
, View.OnClickListener {

    String[] conversoes = new String[] {
            "polegada - cm", "pés - metros", "milha - km", "fahrenheit - celsius", "nós - km/h"
    };

    String[] conversoesAtuais = new String[] {
            "polegada", "cm"
    };

    EditText txt1;
    EditText txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.tvInserirNumero);
        txt2 = findViewById(R.id.tvInserirNumero2);
        txt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt1.isFocused())
                    conversao1();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        txt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txt2.isFocused())
                    conversao2();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        Spinner spinner = (Spinner) findViewById(R.id.spMedidaAtual);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conversoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        conversoesAtuais = conversoes[position].split("-");
        mudandoAmbiente();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void conversao1(){
        String texto = txt1.getText().toString();
        double result = 0;
        if(!texto.isEmpty()){
            Double valor = Double.parseDouble(texto);
            switch (conversoesAtuais[0].trim()){
            case "polegada":
                result = valor * 2.54;
                break;
            case "pés":
                result = valor / 3.281;
                break;
            case "milha":
                result = valor * 1.60934;
                break;
            case "fahrenheit":
                result = (valor -32) * 5/9 ;
                break;
            case "nós":
                result = valor * 1.852;
                break;
            }
        }
        txt2.setText(String.valueOf(result));
    }

    private void conversao2(){
        String texto = txt2.getText().toString();
        double result = 0;
        if(!texto.isEmpty()){
            Double valor = Double.parseDouble(texto);
            switch (conversoesAtuais[1].trim()){
                case "cm":
                    result = valor / 2.54;
                    break;
                case "metros":
                    result = valor * 3.281;
                    break;
                case "km":
                    result = valor / 1.60934;
                    break;
                case "celsius":
                    result = (valor * 9/5) + 32;
                    break;
                case "km/h":
                    result = valor / 1.852;
                    break;
            }
        }
        txt1.setText(String.valueOf(result));
    }

    private void mudandoAmbiente(){
        TextView lbl1 = findViewById(R.id.lblConversor1);
        lbl1.setText(conversoesAtuais[0].trim());
        TextView lbl2 = findViewById(R.id.lblConversor2);
        lbl2.setText(conversoesAtuais[1].trim());
        conversao1();
    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txt1.getWindowToken(), 0);
    }
}