package com.funcionarios.funcionarios.controllers;

import com.funcionarios.funcionarios.models.Carro;
import com.funcionarios.funcionarios.models.CarroDto;
import com.funcionarios.funcionarios.repositorio.CarroRepositorio;
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
@RequestMapping("/carro")
public class CarroControler {


    @Autowired
    private CarroRepositorio repo;
    private final Logger logger = LoggerFactory.getLogger(CarroControler.class);

    @GetMapping({"", "/"})
    public String mostrarCarro(Model model) {
        List<Carro> carro = repo.findAll();
        model.addAttribute("Carro", carro);
        return "carro/ListarCarro";
    }

    @GetMapping("/criar")
    public String mostrarCriarCarro(Model model) {
        CarroDto carrodto = new CarroDto();
        model.addAttribute("CarroDto", carrodto);
        logger.info("Um funcionario foi cadastrado");
        return "carro/CriarCarro";
    }


    @PostMapping("/criar")
    public String criarCarro(Model model, @Valid @ModelAttribute CarroDto carroDto,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("org.springframework.validation.BindingResult.CarroDto", bindingResult);

            return "carro/CriarCarro";
        }

        Carro carro = new Carro();
        carro.setMarca(carroDto.getMarca());
        carro.setModelo(carroDto.getModelo());
        carro.setPlaca(carroDto.getPlaca());

        repo.save(carro);
        logger.info("Um carro foi criado com sucesso!");
        return "redirect:/carro";
    }


    @GetMapping("/edit")
    public String mostrarEditarCarro(Model model, @RequestParam int id) {
        try{
            Carro carro = repo.findById(id).get();
            model.addAttribute("CarroDto", carro);

            Carro carroedit = new Carro();
            carroedit.setId(carro.getId());
            carroedit.setMarca(carro.getMarca());
            carroedit.setModelo(carro.getModelo());
            carroedit.setPlaca(carro.getPlaca());

            model.addAttribute("CarroDto", carroedit);
        }

        catch (Exception ex){
            System.out.println("Erro ao atualizar carro" + ex.getMessage());
            return "http://localhost:8080/carro";
        }
        return "carro/EditCarro";


    }


    @PostMapping("/edit")
    public String updateFuncionario(Model model, @RequestParam int id, @Valid @ModelAttribute CarroDto carroDto,
                                    BindingResult bindingResult) {

        try {
            Carro carro = repo.findById(id).get();
            model.addAttribute("CarroDto", carro);

            if (bindingResult.hasErrors()) {

                model.addAttribute("org.springframework.validation.BindingResult.CarroDto", bindingResult);
                return "/carro/EditCarro";
            }

            carro.setMarca(carroDto.getMarca());
            carro.setModelo(carroDto.getModelo());
            carro.setPlaca(carroDto.getPlaca());
            repo.save(carro);
            logger.info("Um carro foi editado com sucesso");
            return "redirect:/carro";
        } catch (Exception ex) {
            System.out.println("exception:" + ex.getMessage());
        }
        return "redirect:/carro";
    }


    @GetMapping("/delete")
    public String deletarCarro(@RequestParam int id) {

        try {

            Carro carro = repo.findById(id).get();
            repo.delete(carro);
        } catch (Exception ex) {
            System.out.println("exception:" + ex.getMessage());
        }
        logger.info("Um carro foi deletado com sucesso");
        return "redirect:/carro";
    }

}


