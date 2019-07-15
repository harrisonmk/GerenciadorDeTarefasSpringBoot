package com.harrison.GerenciadorDeTarefasSpringBoot.controle;

import com.harrison.GerenciadorDeTarefasSpringBoot.modelo.Usuario;
import com.harrison.GerenciadorDeTarefasSpringBoot.servicos.ServicoUsuario;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContaControle {

    @Autowired
    private ServicoUsuario servicoUsuario;
    
    //metodo para fazer login
    @GetMapping("/login")
    public String login() {

        return "conta/login";

    }
    
    
    //metodo para registrar um usuario
    @GetMapping("/registration")
    public ModelAndView registrar(){
        
       ModelAndView mv = new ModelAndView();
       mv.setViewName("conta/registrar");
       mv.addObject("usuario",new Usuario());
       
       return mv;
        
    }
    
    //metodo para registrar um usuario
    @PostMapping("/registration")
    public ModelAndView registrar(@Valid Usuario usuario,BindingResult result){
        
     ModelAndView mv = new ModelAndView();
     
      Usuario usr = servicoUsuario.encontrarPorEmail(usuario.getEmail());
       if(usr !=null){
           result.rejectValue("email","","Usuario ja Cadastrado");
       } 
       if(result.hasErrors()){
          mv.setViewName("conta/registrar");
          mv.addObject("usuario",usuario);
       }else {
         servicoUsuario.salvarUsuario(usuario);
          mv.setViewName("redirect:/login"); 
       }
       
       return mv;
        
    }
    

}
