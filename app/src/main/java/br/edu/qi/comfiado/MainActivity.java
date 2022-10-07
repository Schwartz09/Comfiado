package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.qi.comfiado.modelo.Usuario;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private Usuario usuario;
    private DatabaseReference mDatabase;

    private void logoff() {
        this.mAuth.signOut();
    }

    private void trocarParaActivityLogin() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mDatabase = FirebaseDatabase.getInstance().getReference().child("usuarios");
        this.mAuth = FirebaseAuth.getInstance();
        this.usuario = new Usuario();

        usuario.setUid(mAuth.getCurrentUser().getUid());

        this.mDatabase.child(usuario.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    usuario = task.getResult().getValue(Usuario.class);

                } else {
                    Toast.makeText(MainActivity.this, "Não foi possivel encontrar seu dados", Toast.LENGTH_LONG).show();
                    logoff();
                    trocarParaActivityLogin();
                }
            }
        });




    }
}