
package com.harrison.GerenciadorDeTarefasSpringBoot.modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usr_usuario") //relaciona com a tabela criada no mysql
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id") //nome que vai ficar no banco de dados
    private Long id;
    
    @Column(name = "usr_email", length = 100, nullable = false) //nome do atributo , tamanho do atributo, nao pode ser nullo ou vazio
    @NotNull(message="O email eh obrigatorio.")
    @Length(min=5,max=100,message="o email deve conter entre 5 e 100 caracteres.")
    private String email;
    
    
   
    @Column(name = "usr_senha", length = 100, nullable = false) //nome do atributo , tamanho do atributo, nao pode ser nullo ou vazio
    @NotNull(message="A senha eh obrigatoria.")
    private String senha;

    //um usuario pode ter várias tarefas
    // o fetch=FetchType.LAZY vai carregar todas as tarefas do usuario de forma manual
    @OneToMany(mappedBy="usuario",fetch=FetchType.LAZY)
    private List<Tarefa> tarefas;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
    
    
    
}
