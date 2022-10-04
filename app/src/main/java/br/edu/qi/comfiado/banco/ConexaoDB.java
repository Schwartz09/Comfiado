package br.edu.qi.comfiado.banco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConexaoDB {

    public static DatabaseReference conectar() {
        return FirebaseDatabase.getInstance().getReference();

    }
}
