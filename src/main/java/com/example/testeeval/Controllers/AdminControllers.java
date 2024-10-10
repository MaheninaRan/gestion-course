package com.example.testeeval.Controllers;

import com.example.testeeval.Services.AdminServices;
import com.example.testeeval.Services.Delete;
import com.example.testeeval.model.*;
import com.example.testeeval.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lag")
public class AdminControllers {

    private final AdminServices adminServices;
    private final CategorieRepository categorieRepository;
    private final EtapeRepository etapeRepository;
    private final CompositionetapeRepository compositionetapeRepository;
    private final VEquipeCategorieRepository vEquipeCategorieRepository;
    private final PenaliteRepository penaliteRepository;
    private final PointRepository pointRepository;
    private final ResultatetapeRepository resultatetapeRepository;
    private final ClassementGeneraleEquipeRepository classementGeneraleEquipeRepository;
    private final CoureurRepository coureurRepository;

    private final ClassementParEtapeRepository classementParEtapeRepository;

    private final Delete delete;
    private final EquipeRepository equipeRepository;
    private final ClassementParEtapeParCoureurPointRepository classementParEtapeParCoureurPointRepository;

    @Autowired
    public AdminControllers(AdminServices adminServices, CategorieRepository categorieRepository, EtapeRepository etapeRepository, CompositionetapeRepository compositionetapeRepository, VEquipeCategorieRepository vEquipeCategorieRepository, PenaliteRepository penaliteRepository, PointRepository pointRepository, ResultatetapeRepository resultatetapeRepository, ClassementGeneraleEquipeRepository classementGeneraleEquipeRepository, CoureurRepository coureurRepository, ClassementParEtapeRepository classementParEtapeRepository, Delete delete,
                            EquipeRepository equipeRepository,
                            ClassementParEtapeParCoureurPointRepository classementParEtapeParCoureurPointRepository) {
        this.adminServices = adminServices;
        this.categorieRepository = categorieRepository;
        this.etapeRepository = etapeRepository;
        this.compositionetapeRepository = compositionetapeRepository;
        this.vEquipeCategorieRepository = vEquipeCategorieRepository;
        this.penaliteRepository = penaliteRepository;
        this.pointRepository = pointRepository;
        this.resultatetapeRepository = resultatetapeRepository;
        this.classementGeneraleEquipeRepository = classementGeneraleEquipeRepository;
        this.coureurRepository = coureurRepository;
        this.classementParEtapeRepository = classementParEtapeRepository;

        this.delete = delete;

        this.equipeRepository = equipeRepository;
        this.classementParEtapeParCoureurPointRepository = classementParEtapeParCoureurPointRepository;
    }
    @RequestMapping()
    public String loginAdmin(){
        return "loginAdmin";
    }

    @GetMapping("/importpoints")
    public String point(){
        return "Admin/Import";
    }
    @GetMapping("/index")
    public String index() throws IOException {
        return "index";
    }

    @RequestMapping("/loginEquipe")
    public String loginEquipe() throws IOException {
        return "loginEquipe";
    }

    @GetMapping("/ImportEtapeResultat")
    public String stringimport(){
        return "Admin/ImportEtapeResultat";
    }


    @GetMapping("/listetape")
    public String test2(Model model){
        List<Etape> etapes = etapeRepository.findAll();
        model.addAttribute("etape",etapes);
        return "Admin/listEtapes";
    }

    @GetMapping("/traveau")
    public String traveau(Model model,@RequestParam("iduser" )String iduser){
        return "Admin/TablesDetaildevis";
    }

    @GetMapping("/detailEtapes")
    public String detailEtapes(Model model, HttpServletRequest request, @RequestParam(value = "id",required = false) String id){
        int idetape = Integer.parseInt(id);
        Optional<Etape> etape = etapeRepository.findById(idetape);
        List<ClassementParEtape> classement_par_etapes =  classementParEtapeRepository.allResultatByEtape(idetape);

        model.addAttribute("listetape",etape.get());
        model.addAttribute("classement",classement_par_etapes);
        return "Admin/detailEtapes";
    }

    @GetMapping("/importView")
    public String importation() {
        return "Admin/Import";
    }

    @GetMapping("/statistique")
    public String statistique(){
        return "Admin/TablesDetaildevis";
    }


    @GetMapping("/classementParequipe")
    public String classementParetape(Model model){
        List<ClassementGeneraleEquipe> list = classementGeneraleEquipeRepository.findTout();
        model.addAttribute("list",list);
        return "Admin/classementequipe";
    }

    @GetMapping("/delete")
    public String delete() throws IOException {
        delete.CleanUpDb();
        return "redirect:/lag/listetape";
    }

    @RequestMapping("/admin")
    public String test1(Model model){
        List<Etape> etapes = etapeRepository.findAll();
        model.addAttribute("etape",etapes);
        return "Admin/listEtapes";
    }

    @RequestMapping("/listecoureur")
    public String traveau(Model model, HttpSession session, @RequestParam("idetape" )String idetape){
        Optional<Etape> etape = this.etapeRepository.findById(Integer.parseInt(idetape));
        int  equipe= ((Admin)session.getAttribute("admin")).getId();
        List<Coureur> coureurs = coureurRepository.findAll();
        model.addAttribute("coureur",coureurs);
        model.addAttribute("etape",etape.get());
        return "Admin/InsertCoureur";
    }

    @Transactional
    @PostMapping("/insert")
    public String insert(@RequestParam("coureur") String Coureur,@RequestParam("etape") String etape,@RequestParam("dates") LocalDateTime dates){
        Optional<Etape> etape1 = this.etapeRepository.findById(Integer.parseInt(etape));
        Optional<Coureur> coureur= this.coureurRepository.findById(Integer.parseInt(Coureur));
        try {
            Compositionetape c=new Compositionetape();

            c.setEtape(etape1.get());
            c.setCoureur(coureur.get());
            Compositionetape compositionetape = compositionetapeRepository.save(c);
            Resultatetape r = new Resultatetape();
            r.setCompositionetape(compositionetape);
            r.setDebut(etape1.get().getDebut());
            r.setFin(Timestamp.valueOf(dates));
            System.out.println(r.getFin());
            resultatetapeRepository.save(r);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/lag/listetape";
    }

    @PostMapping("/existe")
    public String admin(HttpSession httpSession, Model model, @RequestParam("email") String email,@RequestParam("password") String password){
        Optional<Admin> admin = this.adminServices.findlogin(email,password);
        if(admin.isPresent()){
            httpSession.setAttribute("admin",admin.get());
            return "redirect:/lag/admin";
        }
        model.addAttribute("error","Email ou mot de pass invalide");
        return "forward:/lag";
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "index";
    }

    @GetMapping("/penalite")
    public String penalite(Model model,HttpServletRequest request){
        List<Etape> listEtape = etapeRepository.findAll();
        List<Equipe> listEquipe = equipeRepository.findAll();
        List<Penalite> penalites = this.penaliteRepository.findAllVaovao();
        model.addAttribute("penalites", penalites);
        model.addAttribute("etape",listEtape);
        model.addAttribute("equipe",listEquipe);
        return "Admin/penalite";
    }

    @GetMapping("/pdfView")
    public String pdfView(Model model){
        List<ClassementGeneraleEquipe> classementGeneraleEquipe = classementGeneraleEquipeRepository.findTout();
        model.addAttribute("meilleur",classementGeneraleEquipe);
        return "Admin/pdf";
    }

}
