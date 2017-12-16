package ufo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ufo.service.UfoSightingService;
import ufo.service.UfoSightingServiceImpl;

public class UfoSightingTest {

	UfoSightingService service = new UfoSightingServiceImpl();
	
    @Test
    public void testSearch() {
        assertEquals(service.search(1995, 10).size(), 107);
    }
    
    @Test
    public void testGetAllSightings() {
        assertEquals(service.getAllSightings().size(), 61393);
    }
}