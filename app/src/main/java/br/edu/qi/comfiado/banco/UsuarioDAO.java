package br.edu.qi.comfiado.banco;
import com.google.firebase.database.DatabaseReference;


public class UsuarioDAO {

    private final DatabaseReference databaseReference;

    public UsuarioDAO() {
        this.databaseReference = ConexaoDB.conectar();
    }
}
