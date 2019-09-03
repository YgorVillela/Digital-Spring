package br.com.fiap.petshop.controller;

import br.com.fiap.petshop.model.Animal;
import br.com.fiap.petshop.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalRepository rep;

    @PostMapping("/remover")
   public String apagar(int codigo, RedirectAttributes redirectAttributes){
        rep.deleteById(codigo);
        redirectAttributes.addFlashAttribute("msg","Apagado!");
        return "redirect:/animal/listar";
    }

    @PostMapping("/editar")
    public String atualizar(Animal animal, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", "Atualizado!");
        rep.save(animal);
        return "redirect:/animal/listar";
    }


    @GetMapping("/cadastrar")
    public String cadastrar(Animal animal){
        return"animal/form";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("lista", rep.findAll());
        model.addAttribute("msg", "Lista!");
        return "animal/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int codigo , Model model){
        model.addAttribute("animal", rep.findById(codigo)); //Aqui deve receber o objeto
        return "/animal/editar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Animal animal, RedirectAttributes redirectAttributes){
        rep.save(animal);
        redirectAttributes.addFlashAttribute("msg", "Cadastrado!");
        return "redirect:/animal/listar";
    }
}
