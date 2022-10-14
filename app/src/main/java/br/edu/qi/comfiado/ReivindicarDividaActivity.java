package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.qi.comfiado.modelo.Divida;
import br.edu.qi.comfiado.modelo.Usuario;

public class ReivindicarDividaActivity extends AppCompatActivity {

    private EditText edtCodigoDivida;
    private TextView txtInformacoesDivida;
    private Button btnConfirmarDivida;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Divida divida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reivindicar_divida);

        this.edtCodigoDivida = findViewById(R.id.edtCodigoDivida);
        this.txtInformacoesDivida = findViewById(R.id.txtInformacoesDivida);
        this.btnConfirmarDivida = findViewById(R.id.btnConfirmarDivida);

        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();

        this.btnConfirmarDivida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (divida != null) {
                    // TODO: definir a divida como sendo do usuario
                }
            }
        });

        this.edtCodigoDivida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 4) {

                    Query queryDivida = mDatabase.child("dividas").orderByChild("codigo").equalTo(String.valueOf(charSequence));

                    queryDivida.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            divida = snapshot.getValue(Divida.class);

                            Query queryCredor = mDatabase.child("usuarios").child(divida.getUidCredor());

                            queryCredor.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {

                                    String myFormat="dd/MM/yyyy";
                                    SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat);

                                    Usuario credor = task.getResult().getValue(Usuario.class);

                                    StringBuilder sb = new StringBuilder();

                                    sb.append("Nome Credor: " + credor.getNome() + "\n");
                                    sb.append("Valor: " + divida.getValor() + "\n");
                                    sb.append("Descrição: " + divida.getDescricao() + "\n");

                                    Calendar dataAbertura = Calendar.getInstance();
                                    dataAbertura.setTimeInMillis(divida.getDataAbertura());

                                    Calendar dataVencimento = Calendar.getInstance();
                                    dataVencimento.setTimeInMillis(divida.getDataVencimento());

                                    sb.append("Data de Abertura: " + dateFormat.format(dataAbertura.getTime()) + "\n");
                                    sb.append("vencimento: " + dateFormat.format(dataVencimento.getTime()));

                                    txtInformacoesDivida.setText(sb.toString());

                                }
                            });

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
                } else {
                    txtInformacoesDivida.setText("");
                    divida = null;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}