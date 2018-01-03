package ufo.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ufo.dao.UfoSightingDAO;
import ufo.dao.impl.UfoSightingFileDAOImpl;
import ufo.dto.UfoSighting;
import ufo.exception.ServiceException;
import ufo.service.UfoSightingService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UfoSightingServiceImplTest {

    @Test
    public void getAllSightings_IntegrationTest() {
        UfoSightingDAO dao = new UfoSightingFileDAOImpl("ufo_awesome.tsv");
        UfoSightingService service = new UfoSightingServiceImpl(dao);
        List<UfoSighting> allSightings = service.getAllSightings();
        assertEquals(61391, allSightings.size());
    }

    @Test
    public void getAllSightings_ExceptionInDAO() throws Exception {
        UfoSightingDAO dao = Mockito.mock(UfoSightingDAO.class);
        ServiceException mockServiceException = Mockito.mock(ServiceException.class);
        Mockito.when(dao.getAllSightings()).thenThrow(mockServiceException);

        UfoSightingService service = new UfoSightingServiceImpl(dao);
        try {
            service.getAllSightings();
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    public void search_IntegrationTest() {
        UfoSightingDAO dao = new UfoSightingFileDAOImpl("ufo_awesome.tsv");
        UfoSightingService service = new UfoSightingServiceImpl(dao);
        List<UfoSighting> allSightings = service.search(2009, 12);
        assertEquals(274, allSightings.size());

        allSightings = service.search(2010, 8);
        assertEquals(410, allSightings.size());
    }

    @Test
    public void search_ExceptionInDAO() throws Exception {
        UfoSightingDAO dao = Mockito.mock(UfoSightingDAO.class);
        ServiceException mockServiceException = Mockito.mock(ServiceException.class);
        Mockito.when(dao.getAllSightings()).thenThrow(mockServiceException);

        UfoSightingService service = new UfoSightingServiceImpl(dao);
        try {
            service.search(1, 2);
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    public void search_InvalidYear() throws Exception {
        UfoSightingService service = new UfoSightingServiceImpl(null);
        try {
            service.search(1, 2);
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
            Assert.assertTrue(e.getMessage().startsWith("Invalid input for search"));
        }

        try {
            service.search(20111, 2);
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
            Assert.assertTrue(e.getMessage().startsWith("Invalid input for search"));
        }

        try {
            service.search(-2011, 2);
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
            Assert.assertTrue(e.getMessage().startsWith("Invalid input for search"));
        }
    }

    @Test
    public void search_InvalidMonth() throws Exception {
        UfoSightingService service = new UfoSightingServiceImpl(null);
        try {
            service.search(2018, 222);
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
            Assert.assertTrue(e.getMessage().startsWith("Invalid input for search"));
        }

        try {
            service.search(2018, -22);
            Assert.fail("Expected runtime exception not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
            Assert.assertTrue(e.getMessage().startsWith("Invalid input for search"));
        }
    }


    @Test
    public void search_FindMatchingData() throws Exception {
        UfoSightingDAO dao = Mockito.mock(UfoSightingDAO.class);
        List<UfoSighting> mockUfoSightings = buildMockSightings();
        Mockito.when(dao.getAllSightings()).thenReturn(mockUfoSightings);

        UfoSightingService service = new UfoSightingServiceImpl(dao);
        List<UfoSighting> ufoSightings = service.search(2018, 2);
        List<UfoSighting> expectedSightings = buildExpectedSightings();
        Assert.assertEquals(expectedSightings, ufoSightings);
    }

    @Test
    public void search_NoMatchingData() throws Exception {
        UfoSightingDAO dao = Mockito.mock(UfoSightingDAO.class);
        List<UfoSighting> mockUfoSightings = buildMockSightings();
        Mockito.when(dao.getAllSightings()).thenReturn(mockUfoSightings);

        UfoSightingService service = new UfoSightingServiceImpl(dao);
        List<UfoSighting> ufoSightings = service.search(2017, 2);
        Assert.assertTrue(ufoSightings.isEmpty());
    }

    private List<UfoSighting> buildMockSightings() {
        List<UfoSighting> mockSightings = new ArrayList();
        mockSightings.add(new UfoSighting("20180101", "20180101","NY", "Square", "1 min", "Square shaped Object"));
        mockSightings.add(new UfoSighting("20180103", "20180104","LA", "Square", "1 min", "Square shaped Object"));
        mockSightings.add(new UfoSighting("20180201", "20180101","NY", "Square", "1 min", "Square shaped Object"));
        mockSightings.add(new UfoSighting("20180201", "20180101","LA", "Square", "1 min", "Square shaped Object"));
        mockSightings.add(new UfoSighting("20180301", "20180101","NY", "Square", "1 min", "Square shaped Object"));
        return mockSightings;
    }

    private List<UfoSighting> buildExpectedSightings() {
        List<UfoSighting> mockSightings = new ArrayList();
        mockSightings.add(new UfoSighting("20180201", "20180101","NY", "Square", "1 min", "Square shaped Object"));
        mockSightings.add(new UfoSighting("20180201", "20180101","LA", "Square", "1 min", "Square shaped Object"));
        return mockSightings;
    }

}