package ufo.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ufo.dao.UfoSightingDAO;
import ufo.dto.UfoSighting;
import ufo.dto.mapper.UfoSightingMapper;
import ufo.exception.ServiceException;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class UfoSightingFileDAOImpl implements UfoSightingDAO {

    private final Logger logger = LoggerFactory.getLogger(UfoSightingFileDAOImpl.class);
    private String fileLocation;

    // Initialise the File DAO with the File Location
    public UfoSightingFileDAOImpl(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<UfoSighting> getAllSightings() throws ServiceException{

        List<UfoSighting> allSightings;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource(this.fileLocation);
            File inputFile = new File(url.getFile());
            InputStream inputFileStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputFileStream));
            UfoSightingMapper ufoSightingMapper = new UfoSightingMapper();
            allSightings = bufferedReader.lines().parallel().map(ufoSightingMapper).filter(a -> a != null).collect(Collectors.toList());
            bufferedReader.close();

        } catch (FileNotFoundException fileNotFoundException) {
            logger.error(String.format("Unable to read from input file: %s", fileLocation));
            throw new ServiceException(fileNotFoundException);
        } catch (IOException ioException) {
            throw new ServiceException(ioException);
        } catch (Exception exception){
            // to handle potential NullPointerException from url.getFile()
            throw new ServiceException(exception);
        }

        return allSightings;
    }
}
