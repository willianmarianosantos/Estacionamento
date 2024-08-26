package com.funcionarios.funcionarios.repositorio;

import com.funcionarios.funcionarios.models.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionarios, Integer > {




}
