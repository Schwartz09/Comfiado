package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import br.edu.qi.comfiado.modelo.Divida;
import br.edu.qi.comfiado.modelo.Usuario;

public class ReivindicarDividaActivity extends AppCompatActivity {

    private EditText edtCodigoDivida;
    private TextView txtInformacoesDivida;
    private Button btnConfirmarDivida;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reivindicar_divida);

        this.edtCodigoDivida = findViewById(R.id.edtCodigoDivida);
        this.txtInformacoesDivida = findViewById(R.id.txtInformacoesDivida);
        this.btnConfirmarDivida = findViewById(R.id.btnConfirmarDivida);

        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference().child("dividas");

        this.edtCodigoDivida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 4) {

                    Query divida = mDatabase.orderByChild("codigo").equalTo(String.valueOf(charSequence));

                    divida.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                Divida divida = task.getResult().getValue(Divida.class);
                                txtInformacoesDivida.setText(divida.toString());
                            } else {
                                txtInformacoesDivida.setText("INVALIDO");
                            }
                        }
                    });
                } else {
                    txtInformacoesDivida.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}