package ufo.dto.mapper;

import org.junit.Test;
import ufo.dto.UfoSighting;

import static org.junit.Assert.*;

public class UfoSightingMapperTest {

    @Test
    public void apply() {
        UfoSightingMapper mapper = new UfoSightingMapper();
        UfoSighting sighting = mapper.apply("20100809\t20100810\t Oklahoma City, OK\t circle\t55 seconds\tCircular, bright orange object flying north in");
        assertEquals("20100809", sighting.getDateSeen());
        assertEquals("20100810", sighting.getDateReported());
        assertEquals("Oklahoma City, OK", sighting.getPlaceSeen());
        assertEquals("circle", sighting.getShape());
        assertEquals("55 seconds", sighting.getDuration());
        assertEquals("Circular, bright orange object flying north in", sighting.getDescription());
    }

    @Test
    public void apply_NoShapeAndDuration() {
        UfoSightingMapper mapper = new UfoSightingMapper();
        UfoSighting sighting = mapper.apply("20100809\t20100810\t Oklahoma City, OK\t \t \tCircular, bright orange object flying north in");
        assertEquals("20100809", sighting.getDateSeen());
        assertEquals("20100810", sighting.getDateReported());
        assertEquals("Oklahoma City, OK", sighting.getPlaceSeen());
        assertEquals("", sighting.getShape());
        assertEquals("", sighting.getDuration());
        assertEquals("Circular, bright orange object flying north in", sighting.getDescription());
    }

    @Test
    public void apply_InvalidRecord() {
        UfoSightingMapper mapper = new UfoSightingMapper();
        UfoSighting sighting = mapper.apply("Some text not in expected format");
        assertNull(sighting);
    }

    @Test
    public void apply_tabsInDescription() {
        UfoSightingMapper mapper = new UfoSightingMapper();
        UfoSighting sighting = mapper.apply("20100809\t20100810\t Oklahoma City, OK\t circle\t55 seconds\tCircular, bright orange\t object flying north in");
        assertEquals("20100809", sighting.getDateSeen());
        assertEquals("20100810", sighting.getDateReported());
        assertEquals("Oklahoma City, OK", sighting.getPlaceSeen());
        assertEquals("circle", sighting.getShape());
        assertEquals("55 seconds", sighting.getDuration());
        assertEquals("Circular, bright orange object flying north in", sighting.getDescription());
    }


}