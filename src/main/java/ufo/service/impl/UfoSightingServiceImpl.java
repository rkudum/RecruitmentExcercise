package ufo.service.impl;

import ufo.dao.UfoSightingDAO;
import ufo.dto.UfoSighting;
import ufo.exception.ServiceException;
import ufo.service.UfoSightingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UfoSightingServiceImpl implements UfoSightingService {

    private static final int YEAR_AND_MONTH_LENGTH = 6;
    private UfoSightingDAO dao;

    public UfoSightingServiceImpl(UfoSightingDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<UfoSighting> getAllSightings() {
        try {
            Map<String, List<UfoSighting>> allSightings = dao.getAllSightings();
            List<UfoSighting> allSightingsRaw = new ArrayList();
            for (String key:allSightings.keySet()) {
                allSightingsRaw.addAll(allSightings.get(key));
            }
            return allSightingsRaw;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UfoSighting> search(int yearSeen, int monthSeen) {

        String normalisedYear = String.valueOf(yearSeen);
        String normalisedMonth = String.format("%02d", monthSeen);

        if(yearSeen<0 || monthSeen<0 || normalisedYear.length()!=4 || normalisedMonth.length()!=2){
            throw new RuntimeException(String.format("Invalid input for search : [%s, %s]", normalisedYear, normalisedMonth));
        }

        List<UfoSighting> allSightings;
        try {
            return dao.getAllSightings().get(normalisedYear+normalisedMonth);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

}
