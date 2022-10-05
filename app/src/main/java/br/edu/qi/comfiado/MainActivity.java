package br.edu.qi.comfiado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import br.edu.qi.comfiado.banco.UsuarioDAO;
import br.edu.qi.comfiado.modelo.TipoUsuario;
import br.edu.qi.comfiado.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario h = new Usuario();

        h.setNome("Henrique");
        h.setCpf("000.000.000-0");
        h.setEmail("henriquemaliaoliveira5@gmail.com");
        h.setSenha("123@qwe");
        h.setSenhaNumerica("123321");
        h.setTipoUsuario(TipoUsuario.CLIENTE);

        UsuarioDAO d = new UsuarioDAO();

        d.cadastrarUsuario(h).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    d.buscarUsuarioLogado();

                } else {
                    System.out.println("ERRO NO CADASTRO ------------");
                }
            }
        });
    }
}