package br.com.fiap.petshop.resource;

import br.com.fiap.petshop.model.Animal;
import br.com.fiap.petshop.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/animal")
public class AnimalResource {

    @Autowired
    private AnimalRepository rep;

    //Listar
    @GetMapping
    public List<Animal> listar(){
        return rep.findAll();
    }

    //Buscar
    @GetMapping("{id}")
    public Animal buscar(@PathVariable int id){
        return rep.findById(id).get();
    }

    //Criar
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Animal cadastrar(@RequestBody Animal animal){
        return rep.save(animal);
    }

    //Atualizar
    @PutMapping("{id}")
    public Animal atualizar(@RequestBody Animal animal, @PathVariable int id){
        animal.setCodigo(id);
        return rep.save(animal);
    }

    //Deletar
    @DeleteMapping("{id}")
    public void remover(@PathVariable int id){
        rep.deleteById(id);
    }

    //pesquisa
    //URL http://localhost:8080/api/animal/pesquisa?nome=Zé
    @GetMapping("pesquisa")
    public List<Animal> pesquisar(@RequestParam String nome){
        return rep.findByNomeContains(nome);
    }

    @GetMapping("pesquisa/castrado/{castrado}") // foco no {}
    public List<Animal> pesquisar(@PathVariable Boolean castrado) {
        return rep.findByCastrado(castrado);
    }

    //animal/pesquisa/nome?raca=vira

    @GetMapping("pesquisa/raca/{raca}")
    public List<Animal> pesquisarRaca(@RequestParam String raca){
        return rep.findByRaca(raca);
    }

    @GetMapping("pesquisa/{nome}")
    public List<Animal> pesquisar(@PathVariable String nome, @PathVariable String raca){
        return rep.findByNomeContainsAndRacaContains(nome, raca);
    }
}


