package br.edu.qi.comfiado;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.qi.comfiado.modelo.Usuario;

public class Auth {

    private final FirebaseAuth mAuth;

    public Auth() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser usuarioLogado(){
        return this.mAuth.getCurrentUser();
    }

    public Usuario login(Usuario usuario) {

    }

    public boolean cadastrar(Usuario usuario) {

    }

}
