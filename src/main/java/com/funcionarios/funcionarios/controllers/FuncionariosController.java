package com.funcionarios.funcionarios.controllers;

import com.funcionarios.funcionarios.models.FuncionarioDto;
import com.funcionarios.funcionarios.models.Funcionarios;
import com.funcionarios.funcionarios.repositorio.FuncionarioRepositorio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/funcionario")
public class FuncionariosController {

    @Autowired
    private FuncionarioRepositorio repo;
    private final Logger logger = LoggerFactory.getLogger(EstacionamentoController.class);


    @GetMapping({"", "/"})
    public String mostrarFuncionarios(Model model) {
        List<Funcionarios> funcionarios = repo.findAll();
        model.addAttribute("Funcionarios", funcionarios);
        return "funcionario/ListarFuncionarios";
    }

    @GetMapping("/criar")
    public String mostrarCriarFuncionarios(Model model) {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        model.addAttribute("FuncionarioDto", funcionarioDto);
        return "funcionario/CriarFuncionario";
    }

    @PostMapping("/criar")
    public String criarFuncionarios(Model model, @Valid @ModelAttribute FuncionarioDto funcionarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("org.springframework.validation.BindingResult.FuncionarioDto", bindingResult);

            // Artigo para arrumar o erro de exebição de mensagem

            // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/BindingResult.html
            return "funcionario/CriarFuncionario";
        }

        Funcionarios funcionario = new Funcionarios();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setCpf(funcionarioDto.getCpf());
        funcionario.setEmail(funcionarioDto.getEmail());
        logger.info("Um cliente foi cadastrado");
        repo.save(funcionario);
        return "redirect:/funcionario";
    }

    @GetMapping("/edit")
    public String mostrarEditarFuncionarios(Model model, @RequestParam int id) {
        try {
            Funcionarios funcionario = repo.findById(id).get();
            model.addAttribute("FuncionarioDto", funcionario);

            Funcionarios funcionarioedit = new Funcionarios();
            funcionarioedit.setId(funcionario.getId());
            funcionarioedit.setNome(funcionario.getNome());
            funcionarioedit.setCpf(funcionario.getCpf());
            funcionarioedit.setEmail(funcionario.getEmail());

            model.addAttribute("funcionarioedit", funcionarioedit);
        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
            return "http://localhost:8080/funcionario";
        }
        return "funcionario/EditFuncionario";

    }

    @PostMapping("/edit")
    public String updateFuncionario(Model model, @RequestParam int id, @Valid @ModelAttribute FuncionarioDto funcionarioDto, BindingResult bindingResult) {

        try {
            Funcionarios funcionario = repo.findById(id).get();
            model.addAttribute("FuncionarioDto", funcionario);

            if (bindingResult.hasErrors()) {

                model.addAttribute("org.springframework.validation.BindingResult.FuncionarioDto", bindingResult);
                return "/funcionario/EditFuncionario";
            }

            funcionario.setNome(funcionarioDto.getNome());
            funcionario.setCpf(funcionarioDto.getCpf());
            funcionario.setEmail(funcionarioDto.getEmail());
            repo.save(funcionario);
            logger.info("Um cliente foi editado");
            return "redirect:/funcionario";
        } catch (Exception ex) {
            System.out.println("exception:" + ex.getMessage());
        }

        return "redirect:/funcionario";
    }

    @GetMapping("/delete")
    public String deletarFuncionario(@RequestParam int id) {

        try {
            Funcionarios funcionario = repo.findById(id).get();

            repo.delete(funcionario);
        } catch (Exception ex) {
            System.out.println("exception:" + ex.getMessage());
        }
        logger.info("Um cliente foi deletado");
        return "redirect:/funcionario";
    }

}
