
package com.harrison.GerenciadorDeTarefasSpringBoot.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  //diz que essa classe é um controle
public class HomeControle {
    
    @GetMapping("/") // retorna algo
    public ModelAndView home(){
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("home/home");
        mv.addObject("Mensagem", "Mensagem do controle"); //primeiro parametro é o objeto e o segundo a mensagem
        
      return mv;  
        
    }
    
}
