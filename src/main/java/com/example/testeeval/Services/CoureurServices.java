package com.example.testeeval.Services;

import com.example.testeeval.model.Coureur;
import com.example.testeeval.repository.CoureurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoureurServices {
    private final CoureurRepository coureurRepository;

    @Autowired
    public CoureurServices(CoureurRepository coureurRepository) {
        this.coureurRepository = coureurRepository;
    }

    public List<Coureur> findcoureur(String etape, String equipe){
        return this.coureurRepository.findcoureur(etape,equipe);
    }
}
