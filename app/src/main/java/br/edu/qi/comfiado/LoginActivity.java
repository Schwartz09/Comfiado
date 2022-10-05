package br.edu.qi.comfiado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private Button btnCadastro;

    // TODO: implementar lembre-me

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.edtEmail = findViewById(R.id.edtEmail);
        this.edtSenha = findViewById(R.id.edtSenha);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnCadastro = findViewById(R.id.btnCadastro);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        this.btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityCadastro(view);
            }
        });
    }

    private void trocarParaActivityCadastro(View v) {
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
        finish();
    }

    private void trocarParaActivityPrincipal(View v) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void login(View v) {
        String email = this.edtEmail.getText().toString();
        String senha = this.edtSenha.getText().toString();

        if (email.isEmpty()) {
            this.edtEmail.setError("Insira seu email");
        }

        if (senha.isEmpty()) {
            this.edtSenha.setError("Insira sua senha");
        }

        // TODO: Implementar login com o firebase

        // TODO: Se o login tiver sucesso, troca pra activity principal


    }
}