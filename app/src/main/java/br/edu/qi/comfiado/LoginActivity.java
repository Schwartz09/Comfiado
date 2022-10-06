package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtSenha;
    private CheckBox ckLembrar;
    private TextView txtCadastrar;
    private Button btnEntrar;

    private FirebaseAuth mAuth;

    // TODO: implementar lembre-me

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        this.mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Se houver um usuario logado
        if(currentUser != null){

            // recarrega as informações do servidor
            currentUser.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    // Se o usuario logado ainda for valido
                    if (currentUser != null) {

                        // troca pra activity principal
                        trocarParaActivityPrincipal();
                    }
                }
            });

        }

        this.edtUsuario = findViewById(R.id.edtUsuario);
        this.edtSenha = findViewById(R.id.edtSenha);
        this.ckLembrar = findViewById(R.id.ckLembrar);
        this.btnEntrar = findViewById(R.id.btnEntrar);
        this.txtCadastrar = findViewById(R.id.txtCadastrar);

        this.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        this.txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityCadastro();
            }
        });
    }

    private void trocarParaActivityCadastro() {
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
        finish();
    }

    private void trocarParaActivityPrincipal() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void login() {
        String usuario = this.edtUsuario.getText().toString();
        String senha = this.edtSenha.getText().toString();

        if (usuario.isEmpty()) {
            this.edtUsuario.setError("Insira seu email");
        }

        if (senha.isEmpty()) {
            this.edtSenha.setError("Insira sua senha");
        }

        mAuth.signInWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Bem vindo", Toast.LENGTH_SHORT).show();
                            trocarParaActivityPrincipal();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login ou Email INCORRETO", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}