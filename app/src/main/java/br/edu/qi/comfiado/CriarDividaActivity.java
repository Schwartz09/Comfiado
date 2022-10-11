package br.edu.qi.comfiado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CriarDividaActivity extends AppCompatActivity {

    private EditText edtValor;
    private EditText edtDescricao;
    private EditText edtDataVencimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_divida);

        this.edtValor = findViewById(R.id.edtValor);
        this.edtDescricao = findViewById(R.id.edtDescricao);
        this.edtDescricao = findViewById(R.id.edtDataVencimento);

        this.edtDataVencimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}