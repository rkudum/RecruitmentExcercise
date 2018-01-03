package ufo.service.impl;

import ufo.dao.UfoSightingDAO;
import ufo.dto.UfoSighting;
import ufo.exception.ServiceException;
import ufo.service.UfoSightingService;

import java.util.List;
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
            return dao.getAllSightings();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UfoSighting> search(int yearSeen, int monthSeen) {
        String normalisedMonth = String.format("%02d", monthSeen);
        List<UfoSighting> allSightings;
        try {
            allSightings = dao.getAllSightings();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return allSightings.parallelStream().filter(aSighting -> aSighting.getDateSeen().length() > YEAR_AND_MONTH_LENGTH && aSighting.getDateSeen().startsWith(String.valueOf(yearSeen) + normalisedMonth)).collect(Collectors.toList());
    }

}
