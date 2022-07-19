package com.example.ejercicio1S456.controller;

import com.example.ejercicio1S456.entities.Laptop;
import com.example.ejercicio1S456.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {

        this.laptopRepository = laptopRepository;
    }

    //buscar todos los libros
    @GetMapping("/api/laptops")//aca se linkea la url para poder accederlo
    public List<Laptop> findAll() {

        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    //Buscar una laptop por id
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {//esta anotación vincula el param con la url
        //usamos el ResponseEntity para que devuelva una correcto status
        Optional<Laptop> laptopopt = laptopRepository.findById(id);//se usa opcion para envolver el resultado y no tener que trabajar directamente con el valor null
        if (laptopopt.isPresent())
            return ResponseEntity.ok(laptopopt.get());
        else
            return ResponseEntity.notFound().build();//construye una resp del tipo notFound
    }

    //crear una laptop
    //esto convendría hacerlo desde una capa de servicios
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {//traeme la info de la paticion y cargala en un parámetro laptop
        System.out.println(headers.get("User-Agent"));
        if (laptop.getId() != null) {
            log.warn("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //actualizar
    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if(laptop.getId() ==null){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById((laptop.getId()))){
            log.warn("Trying to update a non existent book");
        return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save((laptop));
        return ResponseEntity.ok(result);


    }
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!laptopRepository.existsById((id))){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request for delete all books");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
