package ufo.service.impl;

import ufo.dto.UfoSighting;
import ufo.service.UfoSightingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UfoSightingServiceImpl implements UfoSightingService {

    private static final Logger LOG = Logger.getLogger(UfoSightingServiceImpl.class.getName());

    private final Path ufoFilePath;

    public UfoSightingServiceImpl() {
        ufoFilePath = Paths.get("/ufo_awesome.tsv");
    }

    public UfoSightingServiceImpl(String filePathURI) {
        this.ufoFilePath = Paths.get(filePathURI);
    }

    @Override
    public List<UfoSighting> getAllSightings() {
        return readUfoSightings();
    }

    @Override
    public List<UfoSighting> search(int yearSeen, int monthSeen) {
        return null;
    }

    private List<UfoSighting> readUfoSightings() {
        List<UfoSighting> ufoSightingList = null;

        try (Stream<String> lines = Files.lines(ufoFilePath)) {
            ufoSightingList = lines.map(line -> {
                String[] lineToken = line.split("\t");
                UfoSighting ufoSighting = new UfoSighting(lineToken[0].trim(), lineToken[1].trim(), lineToken[2].trim(), lineToken[3].trim(), lineToken[4].trim(), lineToken[5].trim());
                return ufoSighting;
            }).collect(Collectors.toList());
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return ufoSightingList == null? Collections.emptyList() : ufoSightingList;
    }
}
