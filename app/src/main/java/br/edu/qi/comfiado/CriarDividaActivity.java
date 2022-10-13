package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.qi.comfiado.modelo.Divida;

public class CriarDividaActivity extends AppCompatActivity {

    private EditText edtValor;
    private EditText edtDescricao;
    private EditText edtDataVencimento;
    private DatePickerDialog datePickerDialog;
    private final Calendar calendarDataVencimento = Calendar.getInstance();
    private Button btnCriarDivida;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_divida);
        getSupportActionBar().hide();

        this.edtValor = findViewById(R.id.edtValor);
        this.edtDescricao = findViewById(R.id.edtDescricao);
        this.edtDataVencimento = findViewById(R.id.edtDataVencimento);
        this.btnCriarDivida = findViewById(R.id.btnCriarDivida);
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference().child("dividas");

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendarDataVencimento.set(Calendar.YEAR, year);
                calendarDataVencimento.set(Calendar.MONTH,month);
                calendarDataVencimento.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        this.datePickerDialog = new DatePickerDialog(CriarDividaActivity.this,date,calendarDataVencimento.get(Calendar.YEAR),calendarDataVencimento.get(Calendar.MONTH),calendarDataVencimento.get(Calendar.DAY_OF_MONTH));


        edtDataVencimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!datePickerDialog.isShowing()) {
                    datePickerDialog.show();
                }
            }
        });

        this.btnCriarDivida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Divida divida = new Divida();
                divida.setValor(Float.parseFloat(edtValor.getText().toString()));
                divida.setDescricao(edtDescricao.getText().toString());
                divida.setDataAbertura(Calendar.getInstance().getTimeInMillis());
                divida.setDataVencimento(calendarDataVencimento.getTimeInMillis());
                divida.setUidCredor(mAuth.getCurrentUser().getUid());

                mDatabase.push().setValue(divida).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CriarDividaActivity.this, "Divida Criada", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat);
        edtDataVencimento.setText(dateFormat.format(calendarDataVencimento.getTime()));
    }
}