
package com.harrison.GerenciadorDeTarefasSpringBoot.repositorio;

import com.harrison.GerenciadorDeTarefasSpringBoot.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,Long> {
    
    // faz a busca pelo email com o spring data
    Usuario findByEmail(String email);
    
}
