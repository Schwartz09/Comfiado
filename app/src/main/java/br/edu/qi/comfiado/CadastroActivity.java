package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.qi.comfiado.modelo.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtConfirmacaoSenha;

    private Button btnCadastrar;
    private ImageView imgLogin;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference().child("usuarios");

        // TODO: implementar a tela de cadastro utilizando os ids abaixo

        this.edtNome = findViewById(R.id.edtNome);
        this.edtCpf = findViewById(R.id.edtCpf);
        this.edtTelefone = findViewById(R.id.edtTelefone);
        this.edtEmail = findViewById(R.id.edtEmail);
        this.edtSenha = findViewById(R.id.edtSenha);
        this.edtConfirmacaoSenha = findViewById(R.id.edtConfirmacaoSenha);

        this.imgLogin = findViewById(R.id.imgLogin);
        this.btnCadastrar = findViewById(R.id.btnCadastrar);

        this.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        this.imgLogin.setOnClickListener(new View.OnClickListener() {
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

    private void trocarParaActivityPrincipal() {
        Intent i = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void cadastrar() {

        String nome = this.edtNome.getText().toString();
        String cpf = this.edtCpf.getText().toString();
        String telefone = this.edtTelefone.getText().toString();
        String email = this.edtEmail.getText().toString();
        String senha = this.edtSenha.getText().toString();
        String confirmacaoSenha = this.edtConfirmacaoSenha.getText().toString();

        if (senha.length() < 6) {
            this.edtSenha.setError("A senha deve ter pelo menos 6 caracteres");
            this.edtSenha.requestFocus();
            return;
        }

        if (!confirmacaoSenha.equals(senha)) {
            this.edtConfirmacaoSenha.setError("Senha e Confirmação não coincidem");
            this.edtConfirmacaoSenha.requestFocus();
            return;
        }



        Usuario cadastro = new Usuario();

        cadastro.setNome(nome);
        cadastro.setCpf(cpf);
        cadastro.setTelefone(telefone);
        cadastro.setEmail(email);
        cadastro.setSenha(senha);

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();

                            mDatabase.child(uid).setValue(cadastro);

                            Toast.makeText(CadastroActivity.this, "Bem vindo", Toast.LENGTH_SHORT).show();
                            trocarParaActivityPrincipal();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e) {
                                edtEmail.setError("Email já está em uso");
                                edtEmail.requestFocus();
                            } catch(Exception e) {
                                Toast.makeText(CadastroActivity.this, "Houve um erro", Toast.LENGTH_LONG).show();
                                System.out.println("Erro " + e.getMessage());
                            }
                        }
                    }
                });
    };
}