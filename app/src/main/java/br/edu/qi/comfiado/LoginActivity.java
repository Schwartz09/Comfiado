package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import br.edu.qi.comfiado.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private TextView txtCadastrar;
    private Button btnEntrar;

    private ProgressDialog pdLoadingBar;

    private FirebaseAuth mAuth;

    // TODO: implementar lembre-me

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        this.mAuth = FirebaseAuth.getInstance();
        this.pdLoadingBar = new ProgressDialog(LoginActivity.this);

        FirebaseUser usuario = mAuth.getCurrentUser();

        if(usuario != null){
            usuario.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (usuario != null) {
                        trocarParaActivityPrincipal();
                    }
                }
            });

        }

        this.edtEmail = findViewById(R.id.edtEmail);
        this.edtSenha = findViewById(R.id.edtSenha);
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
        String email = this.edtEmail.getText().toString();
        String senha = this.edtSenha.getText().toString();

        if (email.isEmpty()) {
            this.edtEmail.setError("Insira seu email");
        }else if (senha.isEmpty()) {
            this.edtSenha.setError("Insira sua senha");
        } else {

            pdLoadingBar.setTitle("Entrando...");
            pdLoadingBar.setMessage("Por favor aguarde...");
            pdLoadingBar.setCanceledOnTouchOutside(false);
            pdLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Bem vindo", Toast.LENGTH_SHORT).show();
                                trocarParaActivityPrincipal();
                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(LoginActivity.this, "Email e/ou Senha Incorreto(s)", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    Toast.makeText(LoginActivity.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    System.out.println("Erro " + e.getMessage());
                                }
                            }
                            pdLoadingBar.dismiss();
                        }
                    });
        }
    }
}