package com.funcionarios.funcionarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.funcionarios.funcionarios.models.CarroFuncionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarroFuncionarioRepositorio extends JpaRepository<CarroFuncionario, Integer> {

    @Query("select c from CarroFuncionario c where  c.dataSaida is null")
    public List<CarroFuncionario> listarVagasOcupadas();


    Optional<CarroFuncionario> findByNumeroVagaAndDataSaidaIsNull(Integer numeroVaga);


}