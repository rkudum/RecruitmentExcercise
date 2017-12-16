package ufo.service;

import java.util.List;

public interface UfoSightingService {


    /**
     * Returns all the sightings in the tsv file.
     */
    List<String> getAllSightings();

    /**
     * Search for sightings happened in the specified year and month;
     *
     * @param yearSeen  The year when the sighting happened
     * @param monthSeen The month when the sightings happened
     */
    List<String> search(int yearSeen, int monthSeen);


}
