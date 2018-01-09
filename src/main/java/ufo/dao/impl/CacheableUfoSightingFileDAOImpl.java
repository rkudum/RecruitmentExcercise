package ufo.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ufo.dao.UfoSightingDAO;
import ufo.dto.UfoSighting;
import ufo.dto.mapper.UfoSightingMapper;
import ufo.dto.util.UfoSightingDetails;
import ufo.exception.ServiceException;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CacheableUfoSightingFileDAOImpl implements UfoSightingDAO {

    private static CacheableUfoSightingFileDAOImpl instance = new CacheableUfoSightingFileDAOImpl();
    private Map<String, List<UfoSighting>> allSightings;
    private final Logger logger = LoggerFactory.getLogger(CacheableUfoSightingFileDAOImpl.class);
    private String fileLocation;

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    //Singleton with Cache
    private CacheableUfoSightingFileDAOImpl() {

    }

    public static CacheableUfoSightingFileDAOImpl getInstance() {
        return instance;
    }

    public void loadCache() throws ServiceException {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource(this.fileLocation);
            File inputFile = new File(url.getFile());
            InputStream inputFileStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputFileStream));
            UfoSightingMapper ufoSightingMapper = new UfoSightingMapper();
            allSightings = bufferedReader.lines().map(ufoSightingMapper).filter(a -> a != null).collect(Collectors.groupingBy(UfoSightingDetails::getDateSeenYYYYMM, HashMap::new, Collectors.toList()));
            bufferedReader.close();

        } catch (FileNotFoundException fileNotFoundException) {
            logger.error(String.format("Unable to read from input file: %s", fileLocation));
            throw new ServiceException(fileNotFoundException);
        } catch (IOException ioException) {
            throw new ServiceException(ioException);
        } catch (Exception exception) {
            // to handle potential NullPointerException from url.getFile()
            throw new ServiceException(exception);
        }

    }

    private boolean isLoaded() {
        return allSightings == null ? false : true;
    }

    public Map<String, List<UfoSighting>> getAllSightings() throws ServiceException {
        synchronized (this) {
            if (!isLoaded()) {
                loadCache();
            }
        }
        return allSightings;
    }

}
