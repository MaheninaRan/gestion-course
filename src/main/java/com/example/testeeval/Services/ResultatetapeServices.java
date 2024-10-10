package com.example.testeeval.Services;

import com.example.testeeval.repository.ResultatetapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatetapeServices {

private final ResultatetapeRepository resultatetapeRepository;

@Autowired
    public ResultatetapeServices(ResultatetapeRepository resultatetapeRepository) {
        this.resultatetapeRepository = resultatetapeRepository;
    }


}
