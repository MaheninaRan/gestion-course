package com.example.testeeval.Controllers;

import com.example.testeeval.Services.ResultatetapeServices;
import com.example.testeeval.model.ClassementGeneraleEquipe;
import com.example.testeeval.model.ClassementParEtape;
import com.example.testeeval.repository.ClassementGeneraleEquipeRepository;
import com.example.testeeval.repository.ClassementParEtapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Classement")
public class ClassementControllers {

    private final ResultatetapeServices resultatetapeServices;
    private final ClassementParEtapeRepository classementParEtapeRepository;

    private final ClassementGeneraleEquipeRepository classementGeneraleEquipeRepository;

    @Autowired
    public ClassementControllers(ResultatetapeServices resultatetapeServices, ClassementParEtapeRepository classementParEtapeRepository, ClassementGeneraleEquipeRepository classementGeneraleEquipeRepository) {
        this.resultatetapeServices = resultatetapeServices;
        this.classementParEtapeRepository = classementParEtapeRepository;
        this.classementGeneraleEquipeRepository = classementGeneraleEquipeRepository;
    }

    @GetMapping("/pointequipe")
    public String pointequipe(Model model){
        List<ClassementParEtape> resultatetapes = this.classementParEtapeRepository.findAll();
        model.addAttribute("point",resultatetapes);

        return "Equipe/Tablespoint";
    }

    @GetMapping("/equipeequipe")
    public String equipeequipe(Model model){
        List<ClassementGeneraleEquipe> resultatetapes = classementGeneraleEquipeRepository.findTout();
        model.addAttribute("classementequipe",resultatetapes);

        return "Equipe/Tablesequipe";
    }

    @GetMapping("/pointadmin")
    public String pointadmin(Model model){
        List<ClassementParEtape> resultatetapes = this.classementParEtapeRepository.findAll();
        model.addAttribute("point",resultatetapes);

        return "Admin/Tablespoint";
    }

    @GetMapping("/equipeadmin")
    public String equipeadmin(Model model){
        List<ClassementGeneraleEquipe> resultatetapes = this.classementGeneraleEquipeRepository.findTout();
        model.addAttribute("classementequipe",resultatetapes);
        return "Admin/Tablesequipe";
    }

}
