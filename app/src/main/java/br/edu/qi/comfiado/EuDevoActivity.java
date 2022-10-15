package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import br.edu.qi.comfiado.modelo.Divida;
import br.edu.qi.comfiado.modelo.Usuario;

public class EuDevoActivity extends AppCompatActivity {

    private ListView lvDividas;

    private List<Divida> dividas;
    private Usuario usuario;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eu_devo);

        setTitle("Eu devo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.lvDividas = findViewById(R.id.lvDividas);

        this.dividas = new ArrayList<>();
        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        this.mDatabase = FirebaseDatabase.getInstance().getReference();

        Query queryDividas = this.mDatabase.child("dividas").orderByChild("uidDevedor").equalTo(this.usuario.getUid());

        queryDividas.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Divida divida = snapshot.getValue(Divida.class);
                divida.setUid(snapshot.getKey());

                dividas.add(divida);

                mostrarDividas();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void mostrarDividas() {

        ArrayAdapter<Divida> adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,dividas);

        lvDividas.setAdapter(adaptador);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                trocarParaActivityMain();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void trocarParaActivityMain() {
        Intent i = new Intent(EuDevoActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}