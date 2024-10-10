package com.example.testeeval.Services;

import com.example.testeeval.model.Admin;
import com.example.testeeval.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServices(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findlogin(String email, String password){
        return this.adminRepository.findMail(email,password);

    }

    public List<Admin> findRecherche(String recherche){
        return adminRepository.findrecherche(recherche);
    }

    public List<Admin> findAll(){
        return adminRepository.findAll();
    }
}
