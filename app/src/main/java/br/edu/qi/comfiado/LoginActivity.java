package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private Button btnCadastro;

    private FirebaseAuth mAuth;

    // TODO: implementar lembre-me

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            this.trocarParaActivityPrincipal();
        }

        // TODO: implementar a tela de login com os ids abaixo

        this.edtEmail = findViewById(R.id.edtEmail);
        this.edtSenha = findViewById(R.id.edtSenha);

        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnCadastro = findViewById(R.id.btnCadastro);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        this.btnCadastro.setOnClickListener(new View.OnClickListener() {
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
        String email = this.edtEmail.getText().toString();
        String senha = this.edtSenha.getText().toString();

        if (email.isEmpty()) {
            this.edtEmail.setError("Insira seu email");
        }

        if (senha.isEmpty()) {
            this.edtSenha.setError("Insira sua senha");
        }

        mAuth.signInWithEmailAndPassword(email, senha)
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