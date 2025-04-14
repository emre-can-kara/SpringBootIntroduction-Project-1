package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path= "/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;

    @PostConstruct // Proje ayağa kalkarken method devreye giriyor ve işlemleri yapıyor, obje oluşturulmuş; maymun eklenmiş şekilde geliyor
    public void loadAll() {
        System.out.println("post construct çalıştı.");
        this.animals = new HashMap<>(); // animal objesi oluşturuldu
        this.animals.put(1, new Animal(1, "maymun")); // içine default olarak maymun eklendi
    }

    @GetMapping
    public List<Animal> getAnimals() {
        System.out.println("-----animals get all triggered!");
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") int id) {
        if (id<0){
            System.out.println("id cannot be less than zero! ID:" + id);
            return null;
        }
        return this.animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal newAnimal) {
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable("id") int id) {
        this.animals.remove(id);
    }
}
