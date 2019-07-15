package com.harrison.GerenciadorDeTarefasSpringBoot.servicos;

import com.harrison.GerenciadorDeTarefasSpringBoot.modelo.Usuario;
import com.harrison.GerenciadorDeTarefasSpringBoot.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //metodo para buscar um email do usuario
    public Usuario encontrarPorEmail(String email) {

        return repositorioUsuario.findByEmail(email);

    }

    //metodo para salvar um usuario no banco de dados e criptografar a senha de usuario com hash
    public void salvarUsuario(Usuario usuario) {

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        repositorioUsuario.save(usuario);
    }

}
