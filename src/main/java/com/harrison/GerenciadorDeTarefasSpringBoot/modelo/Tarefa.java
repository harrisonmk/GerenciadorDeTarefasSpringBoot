package com.harrison.GerenciadorDeTarefasSpringBoot.modelo;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tar_tarefas") //relaciona com a tabela criada no mysql
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tar_id") //nome que vai ficar no banco de dados
    private Long id;
   
    @Column(name = "tar_titulo", length = 50, nullable = false) //nome do atributo , tamanho do atributo, nao pode ser nullo ou vazio
    //@NotNull(message= "O titulo eh obrigatorio") //validacao
    @Length(max=50,message="o titulo deve conter ate no maximo 50 caracteres") //validacao tamanho max e min e mensagem
    private String titulo; //nome da tarefa
    
    @Column(name = "tar_descricao", length = 100, nullable = true) //nome do atributo , tamanho do atributo,pode ser nullo ou vazio
    @Length(max=100,message="a descricao deve conter no maximo 100 caracteres") //validacao tamanho max e min e mensagem
    private String descricao; //descricao da tarefa
    
    @Column(name = "tar_data_expiracao", nullable = false)//nome do atributo ,nao pode ser nullo ou vazio
    @DateTimeFormat(pattern="yyyy-MM-dd") //convert para o data no formato americano
    private Date dataExpiracao; // data de expiracao da tarefa
    
    @Column(name = "tar_concluida", nullable = false) //nome do atributo ,nao pode ser nullo ou vazio
    private Boolean estaConcluida = false; // se a tarefa esta concluida | false signica que nao esta | True esta concluida

    //relacao de muitos para um, varias tarefas tem um usuario
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usr_id")
    private Usuario usuario;
    
    //metodos getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public boolean isEstaConcluida() {
        return estaConcluida;
    }

    public void setEstaConcluida(boolean estaConcluida) {
        this.estaConcluida = estaConcluida;
    }

    public Boolean getEstaConcluida() {
        return estaConcluida;
    }

    public void setEstaConcluida(Boolean estaConcluida) {
        this.estaConcluida = estaConcluida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
    
}
