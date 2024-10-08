package com.example.testeeval.Controllers.mappingController;


import com.example.testeeval.model.mappiingEntity.Ipoint;
import com.example.testeeval.repository.mappingRepository.IpointRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ImportPointController {


    @Autowired
    private IpointRepository ipointRepository;

    @Transactional
    @PostMapping(value = "/importpoint", consumes = {"multipart/form-data"})
    public String upload(@RequestPart("point") MultipartFile file) throws IOException {
        this.ipointRepository.saveAll(parseCsvPaye(file));
        this.ipointRepository.topoint();
        this.ipointRepository.deleteAll();
        return "redirect:lag/importView";
    }

    private Set<Ipoint> parseCsvPaye(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<Ipoint> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Ipoint.class);
            CsvToBean<Ipoint> csvToBean =
                    new CsvToBeanBuilder<Ipoint>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> new Ipoint(
                        csvLine.getClassement(),
                        csvLine.getPoints()
                    )).collect(Collectors.toSet());
        }
    }

}
