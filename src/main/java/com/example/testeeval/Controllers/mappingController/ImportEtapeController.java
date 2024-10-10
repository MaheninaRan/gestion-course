package com.example.testeeval.Controllers.mappingController;

import com.example.testeeval.model.mappiingEntity.Ietape;
import com.example.testeeval.model.mappiingEntity.Iresultat;
import com.example.testeeval.repository.mappingRepository.IetapeRepository;
import com.example.testeeval.repository.mappingRepository.IresultatRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/upload")
public class ImportEtapeController {

    @Autowired
    IetapeRepository ietapeRepository;
    @Autowired
    IresultatRepository iresultatRepository;

    @PostMapping("ImportEtapeResultat")
    @Transactional
    public String importEtape(@RequestPart("etape") MultipartFile etape,
                              @RequestPart("resultat") MultipartFile resultat) throws IOException {

        this.ietapeRepository.saveAll(parseCsvEtape(etape));
        this.ietapeRepository.toEtape();
        this.ietapeRepository.deleteAll();

        this.iresultatRepository.saveAll(parseCsvResult(resultat));
        this.iresultatRepository.toGenre();
        this.iresultatRepository.toEquipe();
        this.iresultatRepository.toCoureur();
        this.iresultatRepository.toCompositionEtape();
        this.iresultatRepository.toResultat();
        this.iresultatRepository.deleteAll();

        return "redirect:lag/importView";
    }

    private Set<Iresultat> parseCsvResult(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<Iresultat> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Iresultat.class);
            CsvToBean<Iresultat> csvToBean =
                    new CsvToBeanBuilder<Iresultat>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
                        try {
                            return new Iresultat(
                                csvLine.getEtaperang(),
                                csvLine.getNumdossard(),
                                csvLine.getNom(),
                                csvLine.getGenre(),
                                csvLine.getDatenaissance(),
                                csvLine.getEquipe(),
                                csvLine.getArriver()
                            );
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toSet());
        }
    }

    private Set<Ietape> parseCsvEtape(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<Ietape> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Ietape.class);
            CsvToBean<Ietape> csvToBean =
                    new CsvToBeanBuilder<Ietape>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
                        try {
                            return new Ietape(
                                    csvLine.getEtape(),
                                    csvLine.getLongueur(),
                                    csvLine.getNbcoureur(),
                                    csvLine.getRang(),
                                    csvLine.getDatedepart(),
                                    csvLine.getHeuredepart()
                            );
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toSet());
        }
    }
}
