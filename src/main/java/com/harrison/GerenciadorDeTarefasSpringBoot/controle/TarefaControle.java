package com.harrison.GerenciadorDeTarefasSpringBoot.controle;

import com.harrison.GerenciadorDeTarefasSpringBoot.modelo.Tarefa;
import com.harrison.GerenciadorDeTarefasSpringBoot.modelo.Usuario;
import com.harrison.GerenciadorDeTarefasSpringBoot.repositorio.RepositorioTarefa;
import com.harrison.GerenciadorDeTarefasSpringBoot.servicos.ServicoUsuario;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  //diz que essa classe é um controle
@RequestMapping("/tarefas")
public class TarefaControle {

    @Autowired // automaticamente o spring faz a injecao de dependencias da interface
    private RepositorioTarefa repositorioTarefa;
    
    @Autowired
    private ServicoUsuario servicoUsuario;

    //lista todas as tarefas
    @GetMapping("/listar")
    public ModelAndView listar(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("tarefas/listar");
        String emailUsuario = request.getUserPrincipal().getName();
        mv.addObject("tarefa", repositorioTarefa.carregarTarefasPorUsuarios(emailUsuario));

        return mv;

    }

    //insere as tarefas
    @GetMapping("/inserir")
    public ModelAndView inserir() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("tarefas/inserir");
        mv.addObject("tarefa", new Tarefa());

        return mv;

    }

    //metodo para inserir as tarefas
    @PostMapping("/inserir")
    public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result,HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        // faz a validacao para a data de expiracao não ser inferior a data atual
        if (tarefa.getDataExpiracao() == null) {
            result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "a data de expiracao eh obrigatoria");
        } else {
            if (tarefa.getDataExpiracao().before(new Date())) {
                result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "a data de expiracao não pode ser anterior a data atual");
            }
        }
        //se der certo ele salva se não ele volta com as mensagens de erros
        if (result.hasErrors()) {
            mv.setViewName("tarefas/inserir");
            mv.addObject(tarefa);
        } else {
            String emailUsuario = request.getUserPrincipal().getName();
            Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
            tarefa.setUsuario(usuarioLogado);
            mv.setViewName("redirect:/tarefas/listar");
            repositorioTarefa.save(tarefa);
            
            
        }
        return mv;

    }

    //metodo para alterar uma tarefa passando um Id como parametro
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Tarefa tarefa = repositorioTarefa.getOne(id);
        mv.setViewName("tarefas/alterar");
        mv.addObject("tarefa", tarefa);
        return mv;
    }

    //metodo para alterar uma tarefa
    @PostMapping("/alterar")
    public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result) {

        ModelAndView mv = new ModelAndView();
        // faz a validacao para a data de expiracao não ser inferior a data atual
        if (tarefa.getDataExpiracao() == null) {
            result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "a data de expiracao eh obrigatoria");
        } else {
            if (tarefa.getDataExpiracao().before(new Date())) {
                result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "a data de expiracao não pode ser anterior a data atual");
            }
        }
        //se der certo ele salva se não ele volta com as mensagens de erros
        if (result.hasErrors()) {
            mv.setViewName("tarefas/alterar");
            mv.addObject(tarefa);
        } else {
            mv.setViewName("redirect:/tarefas/listar");
            repositorioTarefa.save(tarefa);
        }
        return mv;

    }

    //metodo para excluir uma tarefa
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {

        repositorioTarefa.deleteById(id);

        return "redirect:/tarefas/listar";
    }

    //metodo para dizer que a tarefa foi concluida
    @GetMapping("/concluir/{id}")
    public String concluir(@PathVariable("id") Long id) {

        Tarefa tarefa = repositorioTarefa.getOne(id);
        tarefa.setEstaConcluida(true);
        repositorioTarefa.save(tarefa);
        return "redirect:/tarefas/listar";
    }

}
