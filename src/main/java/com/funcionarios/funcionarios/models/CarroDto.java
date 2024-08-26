package com.funcionarios.funcionarios.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class CarroDto {

    @NotBlank(message = "A marca do carro é obrigatório")
    private String marca;

    @NotBlank(message = "O modelo do carro é obrigatório")
    private String modelo;

    @NotBlank(message = "A placa é obrigatória")
    private String placa;


    public String getMarca() {return marca;}

    public void setMarca( String marca) {this.marca = marca;}

    public  String getModelo() {return modelo;}

    public void setModelo( String modelo) {this.modelo = modelo;}

    public  String getPlaca() {return placa;}

    public void setPlaca( String placa) {this.placa = placa;}
}