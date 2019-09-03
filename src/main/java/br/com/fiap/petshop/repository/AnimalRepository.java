package br.com.fiap.petshop.repository;

import br.com.fiap.petshop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {

    //pesquisa
    List<Animal> findByNomeContains(String nome);

    //Pesquisar todos os animais castrados
    List<Animal> findByCastrado(boolean castrado);

    //Pesquisar os animais por parte do nome e raça
    List<Animal> findByNomeContainsAndRacaContains(String nome, String raca);

    List<Animal> findByRaca(String raca);
}
