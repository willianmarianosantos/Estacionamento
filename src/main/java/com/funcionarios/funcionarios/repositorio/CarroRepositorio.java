package com.funcionarios.funcionarios.repositorio;

import com.funcionarios.funcionarios.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepositorio extends JpaRepository<Carro, Integer> {



}

