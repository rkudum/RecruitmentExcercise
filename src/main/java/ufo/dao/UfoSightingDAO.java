package ufo.dao;

import ufo.dto.UfoSighting;
import ufo.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UfoSightingDAO {

    Map<String, List<UfoSighting>> getAllSightings() throws ServiceException;

}
