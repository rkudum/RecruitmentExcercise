package ufo.dao;

import ufo.dto.UfoSighting;
import ufo.exception.ServiceException;

import java.util.List;

public interface UfoSightingDAO {

    List<UfoSighting> getAllSightings() throws ServiceException;

}
