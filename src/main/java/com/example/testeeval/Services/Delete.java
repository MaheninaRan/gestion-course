package com.example.testeeval.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class Delete {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void CleanUpDb() throws IOException {
        String jarPath = "D:\\ITUniversity\\Evaluation-Mai\\CleanUP\\dependace\\gen.jar";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarPath, "testepenalite", "postgres", "Mahenina", "admin,categorie");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder sql = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sql.append(line).append("\n");
        }
        jdbcTemplate.execute(sql.toString());
    }

}
