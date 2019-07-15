
package com.harrison.GerenciadorDeTarefasSpringBoot.repositorio;

import com.harrison.GerenciadorDeTarefasSpringBoot.modelo.Tarefa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTarefa extends JpaRepository<Tarefa,Long> { //cria o crud apenas extendendo o jpaRepository e passando a classe de modelo e sua ID
   
    //metodo para carregar as tarefas de um usuario especifico
    @Query("SELECT t FROM Tarefa t WHERE t.usuario.email = :emailUsuario")
    List<Tarefa> carregarTarefasPorUsuarios(@Param("emailUsuario") String email);
    
    
}
