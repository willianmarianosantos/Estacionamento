package com.funcionarios.funcionarios.models;

import jakarta.persistence.*;

@Entity
@Table(name ="ListaCarro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String marca;

    @Column(length = 20)
    private String modelo;

    @Column(length = 20)
    private String placa;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public  String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getPlaca() {return placa;}

    public void setPlaca(String placa) {this.placa = placa;}
}

