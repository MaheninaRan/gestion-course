package com.example.testeeval.Controllers;

import com.example.testeeval.model.*;
import com.example.testeeval.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Classementcategorie")
public class ClassementCategorieControllers {

    private final VEquipeCategorieRepository vEquipeCategorieRepository;
    private final AleaRepository aleaRepository;
    private final ClassementParEtapeParCoureurPointRepository classementParEtapeParCoureurPointRepository;
    private final CategorieRepository categorieRepository;
    private final ClassementGeneraleEquipeCategorieRepository classementGeneraleEquipeCategorieRepository;
    private final EtapeRepository etapeRepository;
    private final ClassementParEtapeRepository classementParEtapeRepository;

    @Autowired
    public ClassementCategorieControllers(VEquipeCategorieRepository vEquipeCategorieRepository, AleaRepository aleaRepository, ClassementParEtapeParCoureurPointRepository classementParEtapeParCoureurPointRepository, CategorieRepository categorieRepository, ClassementGeneraleEquipeCategorieRepository classementGeneraleEquipeCategorieRepository,
                                          EtapeRepository etapeRepository,
                                          ClassementParEtapeRepository classementParEtapeRepository) {
        this.vEquipeCategorieRepository = vEquipeCategorieRepository;
        this.aleaRepository = aleaRepository;
        this.classementParEtapeParCoureurPointRepository = classementParEtapeParCoureurPointRepository;
        this.categorieRepository = categorieRepository;
        this.classementGeneraleEquipeCategorieRepository = classementGeneraleEquipeCategorieRepository;
        this.etapeRepository = etapeRepository;
        this.classementParEtapeRepository = classementParEtapeRepository;
    }

    @GetMapping("/all")
    public String all(Model model){
        List<VEquipeCategorie> vEquipeCategories = vEquipeCategorieRepository.findAllcategoriesum();
        model.addAttribute("vEquipeCategories",vEquipeCategories);
        return "Admin/Allcategorie";
    }

    @PostMapping("/rahamisy")
    public String rahamisycategorie(Model model,@RequestParam("categorie")String categorie,HttpServletRequest request){
        List<ClassementGeneraleEquipeCategorie> vEquipeCategories = this.classementGeneraleEquipeCategorieRepository.findcategorieSum(Integer.parseInt(categorie));
        List<Categorie> categories = this.categorieRepository.findAll();
        Equipe getSessionEquipe = (Equipe) request.getSession().getAttribute("equipe");
        model.addAttribute("categorie",categories);
        model.addAttribute("vequipe",vEquipeCategories);

        if (getSessionEquipe!=null){
            return "Equipe/TabletypeCategorie";
        }else {
            return "Admin/TabletypeCategorie";
        }

    }

    @GetMapping("/categorie")
    public String equipeequipe(Model model, HttpServletRequest request){
        Equipe getSessionEquipe = (Equipe) request.getSession().getAttribute("equipe");
        List<ClassementGeneraleEquipeCategorie> vEquipeCategories = this.classementGeneraleEquipeCategorieRepository.findAll();
        List<Categorie> categories = this.categorieRepository.findAll();
        model.addAttribute("vequipe",vEquipeCategories);
        model.addAttribute("categorie",categories);
        if (getSessionEquipe!=null){
            return "Equipe/TabletypeCategorie";
        }else {
            return "Admin/TabletypeCategorie";
        }
    }


    @GetMapping("/categorie2")
    public String categorie2(Model model, HttpServletRequest request){
        Equipe getSessionEquipe = (Equipe) request.getSession().getAttribute("equipe");
        List<ClassementGeneraleEquipeCategorie> vEquipeCategories = this.classementGeneraleEquipeCategorieRepository.findAll();
        List<Categorie> categories = this.categorieRepository.findAll();
        model.addAttribute("vequipe",vEquipeCategories);
        model.addAttribute("categorie",categories);
            return "Equipe/TabletypeCategorie";

    }

    @GetMapping("/selectall")
    public String selectall(Model model){
        List<ClassementParEtapeParCoureurPoint> list = classementParEtapeParCoureurPointRepository.findAll();
        List<Etape> etapes = etapeRepository.findAll();
        model.addAttribute("classement",list);
        model.addAttribute("etapes",etapes);
        return "Admin/etapePenaliser";
    }

    @PostMapping("/etapeparid")
    public String etapeparid(Model model,@RequestParam("idetape") String idetape){
        List<ClassementParEtapeParCoureurPoint> list = classementParEtapeParCoureurPointRepository.findbyIdEtape(Integer.parseInt(idetape));
        List<Etape> etapes = etapeRepository.findAll();
        model.addAttribute("classement",list);
        model.addAttribute("etapes",etapes);
        return "Admin/etapePenaliser";
    }

    @GetMapping("lien")
    public String lien(@RequestParam("idequipe") String idequipe,Model model){
        System.out.println("IDEQUIPE" + idequipe );
        List<Alea> classement_par_etape = aleaRepository.yaya(Integer.parseInt(idequipe));
        System.out.println(classement_par_etape.size());
        model.addAttribute("liste",classement_par_etape);
        return "Admin/detail";
    }
}
