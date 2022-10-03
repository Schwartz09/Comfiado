package br.edu.qi.comfiado;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
