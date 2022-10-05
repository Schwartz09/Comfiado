package br.edu.qi.comfiado.banco;

import com.google.firebase.database.DatabaseReference;

public class DividaDAO {

    private final DatabaseReference databaseReference;

    public DividaDAO() {
        this.databaseReference = ConexaoDB.conectar("dividas");
    }
}
