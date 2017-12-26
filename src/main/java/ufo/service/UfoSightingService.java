package ufo.service;

import ufo.dto.UfoSighting;

import java.util.List;


/**
 * TODO://Few suggestions
 * <ul>
 *     <li>The getAllSightings() and search() should give a Set rather than a List to ensure unique sightings and eliminate duplicates</li>
 * </ul>
 */
public interface UfoSightingService {


    /**
     * Returns all the sightings in the tsv file.
     */
    List<UfoSighting> getAllSightings();

    /**
     * Search for sightings happened in the specified year and month;
     *
     * @param yearSeen  The year when the sighting happened
     * @param monthSeen The month when the sightings happened
     */
    List<UfoSighting> search(int yearSeen, int monthSeen);


}
