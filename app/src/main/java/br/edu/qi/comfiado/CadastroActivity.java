package br.edu.qi.comfiado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {


    private Button btnCadastro;
    private Button btnLogin;

    // TODO: Criar atributos edtText para os campos do cadastro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.btnCadastro = findViewById(R.id.btnCadastro);
        this.btnLogin = findViewById(R.id.btnLogin);

        // TODO: definir atributos edtText pelo id

        this.btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityLogin();
            }
        });
    }

    private void trocarParaActivityLogin() {
        Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void trocarParaActivityPrincipal(View v) {
        Intent i = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void cadastrar() {
        // TODO: Implementar cadastro pelo firebase
        // TODO: Se o cadastro for bem sucedido, troca pra activity principal.
    };
}