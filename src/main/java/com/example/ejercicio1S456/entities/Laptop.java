package com.example.ejercicio1S456.entities;

import javax.persistence.*;

@Entity
@Table(name = "ordenadores")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private Double precio;
    private Boolean tactil;

    public Laptop() {

    }

    public Laptop(Long id, String modelo, Double precio, Boolean tactil) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.tactil = tactil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getTactil() {
        return tactil;
    }

    public void setTactil(Boolean tactil) {
        this.tactil = tactil;
    }
}
