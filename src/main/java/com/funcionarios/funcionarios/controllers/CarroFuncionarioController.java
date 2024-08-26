package com.funcionarios.funcionarios.controllers;

import com.funcionarios.funcionarios.repositorio.CarroFuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.funcionarios.funcionarios.models.CarroFuncionario;


import java.util.List;

@RestController
@RequestMapping("/carro-funcionario")
public class CarroFuncionarioController {

    @Autowired
    private CarroFuncionarioRepositorio carroFuncionarioRepositorio;

    @GetMapping
    public List<CarroFuncionario> getAll() {
        return carroFuncionarioRepositorio.findAll();
    }

    @PostMapping
    public CarroFuncionario create(@RequestBody CarroFuncionario carroFuncionario) {
        return carroFuncionarioRepositorio.save(carroFuncionario);
    }

    @PutMapping("/{id}")
    public CarroFuncionario update(@PathVariable int id, @RequestBody CarroFuncionario carroFuncionario) {
        carroFuncionario.setId(id);
        return carroFuncionarioRepositorio.save(carroFuncionario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        carroFuncionarioRepositorio.deleteById(id);
    }
}
