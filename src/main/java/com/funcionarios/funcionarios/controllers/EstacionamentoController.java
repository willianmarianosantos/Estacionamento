package com.funcionarios.funcionarios.controllers;

import com.funcionarios.funcionarios.models.Carro;
import com.funcionarios.funcionarios.models.CarroFuncionario;
import com.funcionarios.funcionarios.models.Funcionarios;
import com.funcionarios.funcionarios.models.VagaDto;
import com.funcionarios.funcionarios.repositorio.CarroFuncionarioRepositorio;
import com.funcionarios.funcionarios.repositorio.CarroRepositorio;
import com.funcionarios.funcionarios.repositorio.FuncionarioRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class EstacionamentoController {


    @Autowired
    private CarroRepositorio CarroRepositorio;

    @Autowired
    private FuncionarioRepositorio FuncionarioRepositorio;

    @Autowired
    private CarroFuncionarioRepositorio CarroFuncionarioRepositorio;
    private final Logger logger = LoggerFactory.getLogger(EstacionamentoController.class);

    @GetMapping("/VagaEstacionamento")
    public String mostrarVagas(@RequestParam(value = "id", required = false) Integer id, Model model) {

        List<Carro> carros = CarroRepositorio.findAll();
        List<Funcionarios> funcionarios = FuncionarioRepositorio.findAll();
        List<CarroFuncionario> vagasOcupadasList = CarroFuncionarioRepositorio.listarVagasOcupadas();


        Set<Integer> vagasOcupadas = vagasOcupadasList.stream()
                .map(CarroFuncionario::getNumeroVaga)
                .collect(Collectors.toSet());

        List<VagaDto> carrosVagasOcupadas = vagasOcupadasList.stream()
                .map(cf -> new VagaDto(
                        cf.getNumeroVaga(),
                        cf.getCarro().getMarca(),
                        cf.getCarro().getModelo(),
                        cf.getCarro().getPlaca(),
                        cf.getFuncionario().getNome(),
                        cf.getFuncionario().getCpf()
                ))
                .collect(Collectors.toList());

        model.addAttribute("carros", carros);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("vagaId", id);
        model.addAttribute("vagasOcupadas", vagasOcupadas);
        model.addAttribute("carrosVagasOcupadas", vagasOcupadas);
        model.addAttribute("carrosVagasOcupadas", carrosVagasOcupadas);

        return "Vagas/vagaEstacionamento";
    }


    @PostMapping("/vaga/ocupar")
    public String ocuparVaga(@RequestParam(value = "carroId", required = false) Integer carroId,
                             @RequestParam(value = "funcionarioId", required = false) Integer funcionarioId,
                             @RequestParam(value = "vagaId", required = false) Integer vagaId,
                             Model model) {

        if (carroId == null || funcionarioId == null || vagaId == null) {
            return "redirect:/VagaEstacionamento";
        }


        Carro carro = CarroRepositorio.findById(carroId).orElseThrow();
        Funcionarios funcionario = FuncionarioRepositorio.findById(funcionarioId).orElseThrow();

        CarroFuncionario carroFuncionario = new CarroFuncionario();
        carroFuncionario.setCarro(carro);
        carroFuncionario.setFuncionario(funcionario);
        carroFuncionario.setDataEntrada(LocalDateTime.now());
        carroFuncionario.setNumeroVaga(vagaId);

        CarroFuncionarioRepositorio.save(carroFuncionario);
        logger.info("Um carro foi cadastrado");
        return "redirect:/VagaEstacionamento";
    }

    @PostMapping("/vaga/desocupar")
    public String desocuparVaga(@RequestParam("vagaId") Integer vagaId) {
        // Busca o registro de CarroFuncionario com a vaga correspondente
        CarroFuncionario carroFuncionario = CarroFuncionarioRepositorio.findByNumeroVagaAndDataSaidaIsNull(vagaId)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada ou já desocupada"));

        CarroFuncionarioRepositorio.delete(carroFuncionario);
        logger.info("Um carro foi removido");
        return "redirect:/VagaEstacionamento";
    }

}


// Cria um conjunto de números de vagas ocupadas, mostrar para seiji

// stream() "ou traduzido fluxo" cria um fluxo (stream) de elementos a partir dessa lista. Streams permitem processar elementos de forma funcional e declarativa, facilitando operações como mapeamento, filtragem e coleta.
// .map() é uma operação intermediária que aplica uma função a cada elemento do fluxo.

// CarroFuncionario::getNumeroVaga refere-se a um método de referência (method reference) que chama o método getNumeroVaga() em cada objeto CarroFuncionario presente na lista.
// getNumeroVaga() retorna o número da vaga associado a esse objeto.

// collect(Collectors.toSet()):
// .collect() é uma operação terminal que converte o fluxo de volta para um tipo de coleção, neste caso, um Set.
// Collectors.toSet() é o coletor que reúne todos os elementos mapeados (os números das vagas) em um Set. Um Set é uma coleção que não permite elementos duplicados.
// Isso é útil quando você precisa verificar ou trabalhar com as vagas ocupadas sem se preocupar com duplicações.

