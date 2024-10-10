package com.example.testeeval.Services;

import com.example.testeeval.model.Equipe;
import com.example.testeeval.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EquipeServices {

    private final EquipeRepository equipeRepository;

    @Autowired
    public EquipeServices(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public Optional<Equipe> findlogin(String email, String password){
        return this.equipeRepository.findLogin(email,password);
    }
    public Optional<Equipe> findEmail(String email){
        return this.equipeRepository.findEmail(email);
    }
}
