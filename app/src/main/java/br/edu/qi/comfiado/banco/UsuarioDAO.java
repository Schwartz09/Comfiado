package br.edu.qi.comfiado.banco;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import br.edu.qi.comfiado.modelo.Usuario;


public class UsuarioDAO {

    private final DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    public UsuarioDAO() {
        this.databaseReference = ConexaoDB.conectar("usuarios");
        this.mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> cadastrarUsuario(Usuario usuario) {
        return mAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String userUid = task.getResult().getUser().getUid();

                            databaseReference.child(userUid).setValue(usuario);

                            usuario.setUid(mAuth.getCurrentUser().getUid());

                        }
                    }
                });
    };

    public Task<DataSnapshot> buscarUsuarioLogado() {
        String uid = mAuth.getCurrentUser().getUid();
        return this.databaseReference.child(uid).get();
    };
}
