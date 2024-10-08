package com.example.testeeval.Controllers;

import com.example.testeeval.Services.CoureurServices;
import com.example.testeeval.Services.EquipeServices;
import com.example.testeeval.model.*;
import com.example.testeeval.Util.Util;

import com.example.testeeval.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/equipe")
public class EquipeControllers {

    private  final VResultatCoureurRepository vResultatCoureurRepository;
    private final EquipeServices equipeServices;
    private final EtapeRepository etapeRepository;
    private final EquipeRepository equipeRepository;
    private final CoureurcategorieRepository coureurcategorieRepository;
    private final CoureurRepository coureurRepository;
    private final CategorieRepository categorieRepository;
    private final ClassementParEtapeParCoureurPointRepository classementParEtapeParCoureurPointRepository;
    private final CoureurServices coureurServices;
    private final ClassementParEtapeRepository classementParEtapeRepository;

    private final CompositionetapeRepository compositionetapeRepository;

    private final ClassementGeneraleEquipeRepository classementGeneraleEquipeRepository;


    @Autowired
    public EquipeControllers(VResultatCoureurRepository vResultatCoureurRepository, EquipeServices equipeServices, EtapeRepository etapeRepository, EquipeRepository equipeRepository, CoureurcategorieRepository coureurcategorieRepository, CoureurRepository coureurRepository, CategorieRepository categorieRepository, ClassementParEtapeParCoureurPointRepository classementParEtapeParCoureurPointRepository, CoureurServices coureurServices, ClassementParEtapeRepository classementParEtapeRepository, CompositionetapeRepository compositionetapeRepository, ClassementGeneraleEquipeRepository classementGeneraleEquipeRepository) {
        this.vResultatCoureurRepository = vResultatCoureurRepository;
        this.equipeServices = equipeServices;
        this.etapeRepository = etapeRepository;
        this.equipeRepository = equipeRepository;
        this.coureurcategorieRepository = coureurcategorieRepository;
        this.coureurRepository = coureurRepository;
        this.categorieRepository = categorieRepository;
        this.classementParEtapeParCoureurPointRepository = classementParEtapeParCoureurPointRepository;
        this.coureurServices = coureurServices;
        this.classementParEtapeRepository = classementParEtapeRepository;
        this.compositionetapeRepository = compositionetapeRepository;
        this.classementGeneraleEquipeRepository = classementGeneraleEquipeRepository;
    }

    @RequestMapping("/equipe")
    public String test1(Model model,HttpSession session){
        int  equipe= ((Equipe)session.getAttribute("equipe")).getId();
        List<VResultatCoureur> vResultatCoureurs =this.vResultatCoureurRepository.findParequipe(equipe);
        model.addAttribute("vResultatCoureurs",vResultatCoureurs);
        return "Equipe/Accueil";
    }

    @RequestMapping("/listecoureur")
    public String traveau(Model model, HttpSession session, @RequestParam("idetape" )String idetape){
        Optional<Etape> etape = this.etapeRepository.findByetape(Integer.parseInt(idetape));
        int  equipe= ((Equipe)session.getAttribute("equipe")).getId();
        List<Coureur> coureurs = coureurRepository.findcoureur(String.valueOf(etape.get().getId()),String.valueOf(equipe));
        List<Coureur> coureurEfaAo = coureurRepository.findcoureurEfaMikrosy(String.valueOf(etape.get().getId()),String.valueOf(equipe));
        int countCoureurAo = coureurEfaAo.size();
        int countCoureurEtape = etape.get().getNbcoureur();
            if (countCoureurAo<countCoureurEtape){
                model.addAttribute("coureur",coureurs);
                model.addAttribute("etape",etape.get());
                return "Equipe/InsertCoureur";
            }else{
                List<VResultatCoureur> vResultatCoureurs =this.vResultatCoureurRepository.findParequipe(equipe);
                model.addAttribute("vResultatCoureurs",vResultatCoureurs);
                model.addAttribute("erreur","EFA FENO LE COUREUR AO");
                return "Equipe/Accueil";
            }
    }

    @GetMapping("/detailEtapes")
    public String detailEtapes(Model model, HttpServletRequest request, @RequestParam(value = "id",required = false) String id){
        int idetape = Integer.parseInt(id);
        Optional<Etape> etape = etapeRepository.findById(idetape);
        Equipe getSessionEquipe = (Equipe) request.getSession().getAttribute("equipe");
        List<ClassementParEtape> classement_par_etapes =  classementParEtapeRepository.allResultatByEtape(idetape);
        List<Coureur> coureurs = coureurRepository.allCoureurMbolatsymikrosy(idetape,getSessionEquipe.getId());
        model.addAttribute("listetape",etape.get());
        model.addAttribute("listCoureur",coureurs);
        model.addAttribute("classement",classement_par_etapes);
        return "Equipe/detailEtapes";
    }

    @PostMapping("/entrerCoureur")
    public String entrerCoureur(Model model, HttpServletRequest request,@RequestParam("idcoureur") String idcoureur,@RequestParam("idetape") String idetape){
        Compositionetape composition = new Compositionetape();
        composition.setCoureur(new Coureur(Integer.parseInt(idcoureur)));
        composition.setEtape(new Etape(Integer.parseInt(idetape)));
        Equipe getSessionEquipe = (Equipe) request.getSession().getAttribute("equipe");
        compositionetapeRepository.save(composition);
        List<Coureur> listCoureurEquipe = coureurRepository.allCoureurMbolatsymikrosy(Integer.parseInt(idetape),getSessionEquipe.getId());
        List<Equipe> equipes = equipeRepository.findByComposition(Integer.parseInt(idetape));
        List<ClassementParEtape> classement_par_etapes =  classementParEtapeRepository.allResultatByEtape(Integer.parseInt(idetape));
        Optional<Etape> etape = etapeRepository.findById(Integer.valueOf(idetape));
        model.addAttribute("listetape",etape.get());
        model.addAttribute("listCoureur",listCoureurEquipe);
        model.addAttribute("classement",classement_par_etapes);
        return "Equipe/detailEtapes";
    }





    @GetMapping("/listetape")
    public String test2(Model model){
        List<Etape> etapes = etapeRepository.findAll();
        model.addAttribute("etape",etapes);
        return "Equipe/listEtapes";
    }

    @GetMapping("/generer")
    public String generer(){
        List<Coureur> coureurs = this.coureurRepository.findAll();
        System.out.println("MITSOFOKA GENGENGENGEN");
        try{
            System.out.println("MIDITRA ATY TRY");
           for(Coureur coureur :coureurs){
               int ismineur= Util.calculerAge(coureur.getDtn());
               int ismajeur= Util.calculerAge(coureur.getDtn());
               if(ismineur<18 && coureur.getGenre().getId()==1){ // genre2 = Lelah @Eval(projet)
                   System.out.println("MISY OLONA MINEUR HOMME");
                   Coureurcategorie coureurcategorie = new Coureurcategorie();
                   coureurcategorie.setCategorie(new Categorie(1));
                   coureurcategorie.setCoureur(new Coureur(coureur.getId()));
                   System.out.println("AVANT INSERT");
                   coureurcategorieRepository.save(coureurcategorie);
                   Coureurcategorie coureurcategorie2 = new Coureurcategorie();
                   coureurcategorie2.setCategorie(new Categorie(3));
                   coureurcategorie2.setCoureur(new Coureur(coureur.getId()));
                   coureurcategorieRepository.save(coureurcategorie2);
               }
               if(ismineur<18 && coureur.getGenre().getId()==2){
                   Coureurcategorie coureurcategorie = new Coureurcategorie();
                   coureurcategorie.setCategorie(new Categorie(2));
                   coureurcategorie.setCoureur(new Coureur(coureur.getId()));
                   coureurcategorieRepository.save(coureurcategorie);
                   Coureurcategorie coureurcategorie2 = new Coureurcategorie();
                   coureurcategorie2.setCategorie(new Categorie(3));
                   coureurcategorie2.setCoureur(new Coureur(coureur.getId()));
                   coureurcategorieRepository.save(coureurcategorie2);
               }
               if(ismajeur>=18 && coureur.getGenre().getId()==1){
                   Coureurcategorie coureurcategorie = new Coureurcategorie();
                   coureurcategorie.setCategorie(new Categorie(1));
                   coureurcategorie.setCoureur(new Coureur(coureur.getId()));
                   coureurcategorieRepository.save(coureurcategorie);
               }
               if(ismajeur>=18 && coureur.getGenre().getId()==2){
                   Coureurcategorie coureurcategorie = new Coureurcategorie();
                   coureurcategorie.setCategorie(new Categorie(2));
                   coureurcategorie.setCoureur(new Coureur(coureur.getId()));
                   coureurcategorieRepository.save(coureurcategorie);
               }

       }
           }catch (Exception e){
               e.printStackTrace();
           }
        return"redirect:/lag/admin";
    }

    @RequestMapping()
    public String test(){
        return "login";
    }

    @Transactional
    @PostMapping("/insert")
    public String insert(Model model,@RequestParam("coureur") String Coureur,@RequestParam("etape") String etape,HttpSession session){
        int  equipe= ((Equipe)session.getAttribute("equipe")).getId();
        List<VResultatCoureur> vResultatCoureurs =this.vResultatCoureurRepository.findParequipe(equipe);
        model.addAttribute("vResultatCoureurs",vResultatCoureurs);
        try {
            Compositionetape c = new Compositionetape();
            c.setEtape(new Etape(Integer.parseInt(etape)));
            c.setCoureur(new Coureur(Integer.parseInt(Coureur)));
            compositionetapeRepository.save(c);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Equipe/Accueil";
    }

    @PostMapping("/existe")
    public ResponseEntity<HashMap<String, String>> equipe(HttpSession httpSession, Model model, @RequestParam("email") String email, @RequestParam("password") String password){
        HashMap<String, String> result = new HashMap<>();
        Optional<Equipe> emailEquipe = this.equipeServices.findEmail(email);
        try{
            if (emailEquipe.isPresent()){
                if(emailEquipe.get().getMdp().equals(password)){
                    httpSession.setAttribute("equipe",emailEquipe.get());
                    result.put("data",emailEquipe.get().getNom());
                }else{
                    throw new Exception("Password error");
                }
            }else{
                throw new Exception("Email n'existe pas");
            }
            result.put("etat","succes");

        }catch(Exception e){
            result.put("etat", "erreur");
            result.put("data", e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("equipe");
        return "index";
    }

    @GetMapping("/classementParequipe")
    public String classementParetape(Model model){
        List<ClassementGeneraleEquipe> list = classementGeneraleEquipeRepository.findTout();
        model.addAttribute("list",list);
        return "Equipe/classementequipe";
    }

}
