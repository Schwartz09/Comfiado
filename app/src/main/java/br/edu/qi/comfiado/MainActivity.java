package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

import br.edu.qi.comfiado.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    private Button btnLogoff;

    private Button btnCriarDivida;
    private Button btnReivindicarDivida;

    private Button btnEuDevo;
    private Button btnMeDevem;

    private TextView txtNomeUsuario;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        this.btnLogoff = findViewById(R.id.btnLogoff);
        this.btnCriarDivida = findViewById(R.id.btnCriarDivida);
        this.btnReivindicarDivida = findViewById(R.id.btnReivindicarDivida);

        this.btnMeDevem = findViewById(R.id.btnMeDevem);
        this.btnEuDevo = findViewById(R.id.btnEuDevo);

        this.mDatabase = FirebaseDatabase.getInstance().getReference().child("usuarios");
        this.mAuth = FirebaseAuth.getInstance();

        this.txtNomeUsuario = findViewById(R.id.txtNomeUsuario);

        this.usuario = new Usuario();
        usuario.setUid(mAuth.getCurrentUser().getUid());

        this.mDatabase.child(usuario.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    usuario = task.getResult().getValue(Usuario.class);
                    if (usuario != null) {
                        usuario.setUid(task.getResult().getKey());
                        txtNomeUsuario.setText("Olá " + usuario.getNome());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Não foi possivel encontrar seu dados", Toast.LENGTH_LONG).show();
                    logoff();
                }
            }
        });

        this.btnCriarDivida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityCriarDivida();
            }
        });

        this.btnReivindicarDivida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityReivindicarDivida();
            }
        });

        this.btnLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoff();
            }
        });

        this.btnEuDevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityEuDevo();
            }
        });

        this.btnMeDevem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarParaActivityMeDevem();
            }
        });
    }

    private void logoff() {
        this.mAuth.signOut();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void trocarParaActivityEuDevo() {
        Intent i = new Intent(MainActivity.this, EuDevoActivity.class);
        i.putExtra("usuario", this.usuario);
        startActivity(i);

    }

    private void trocarParaActivityMeDevem() {
        Intent i = new Intent(MainActivity.this, MeDevemActivity.class);
        i.putExtra("usuario", this.usuario);
        startActivity(i);


    }

    private void trocarParaActivityCriarDivida() {
        Intent i = new Intent(MainActivity.this, CriarDividaActivity.class);
        i.putExtra("usuario", this.usuario);
        startActivity(i);

    }

    private void trocarParaActivityReivindicarDivida(){
        Intent i = new Intent(MainActivity.this, ReivindicarDividaActivity.class);
        i.putExtra("usuario", this.usuario);
        startActivity(i);

    }
}