package com.example.testeeval.Controllers;

import com.example.testeeval.model.Equipe;
import com.example.testeeval.model.Etape;
import com.example.testeeval.model.Penalite;
import com.example.testeeval.repository.EquipeRepository;
import com.example.testeeval.repository.EtapeRepository;
import com.example.testeeval.repository.PenaliteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/penalite")
public class PenaliteController {

    @Autowired
    PenaliteRepository penaliteRepository;
    @Autowired
    EtapeRepository etapeRepository;
    @Autowired
    EquipeRepository equipeRepository;

    @RequestMapping
    public String page(Model model){
        List<Penalite> penalites = this.penaliteRepository.findAllVaovao();
        List<Etape> etapes = this.etapeRepository.findAll();
        List<Equipe> equipes = this.equipeRepository.findAll();
        model.addAttribute("penalites", penalites);
        model.addAttribute("etape", etapes);
        model.addAttribute("equipe", equipes);
        return "Admin/penalite";
    }

    @PostMapping("/add")
    public String addPenality(@RequestParam("etape") int etape,
                              @RequestParam("equipe") int equipe,
                              @RequestParam("hm") String hm,
                              @RequestParam("ss") String ss) {

        Penalite p = new Penalite();
        p.setEquipe(equipe);
        p.setEtape(etape);
        p.setPenalite(Time.valueOf(hm+":"+ss));

        this.penaliteRepository.save(p);
        return "redirect:/penalite";
    }

    @PostMapping("/remove")
    @Transactional
    public String remPenality(@RequestParam(value = "idequipe",required = false) String equipe_id, @RequestParam(value = "idetape",required = false) String etape_id) {
        System.out.println("EQUIP ETAPE: " + equipe_id + etape_id);
        this.penaliteRepository.deletePenaliteByidEquipeAndIdEtpae(Integer.parseInt(equipe_id), Integer.parseInt(etape_id));

        return "redirect:/penalite";
    }
}
