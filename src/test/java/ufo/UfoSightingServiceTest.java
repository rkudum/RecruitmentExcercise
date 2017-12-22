package ufo;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import ufo.dto.UfoSighting;
import ufo.service.UfoSightingService;
import ufo.service.impl.UfoSightingServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UfoSightingServiceTest {
    private UfoSightingService SUT;

    private final String TEST_UFO_DATA = UfoSightingServiceTest.class.getResource("/ufo_awesome_test_data.tsv").getPath();

    private long UFO_COUNT = 0;
    private List<UfoSighting> expectedUfoResults = null;

    @Before
    public void setup() {
        SUT = new UfoSightingServiceImpl(TEST_UFO_DATA);
    }

    @Test
    public void getAllUfoSightingsTest() {
        //Given
        countUfo();
        getExpectedUfoSightingItems();


        //When
        List<UfoSighting> actualSightings = SUT.getAllSightings();

        //Then
        assertEquals(UFO_COUNT, actualSightings.size());


        if (actualSightings.size() > 0) {
            int index = 0;

            for (UfoSighting actualResult : actualSightings) {
                assertThat(actualResult, hasProperty("dateSeen"));
                assertEquals(expectedUfoResults.get(index).getDateSeen(), actualResult.getDateSeen());

                assertThat(actualResult, hasProperty("dateReported"));
                assertEquals(expectedUfoResults.get(index).getDateReported(), actualResult.getDateReported());

                assertThat(actualResult, hasProperty("placeSeen"));
                assertEquals(expectedUfoResults.get(index).getPlaceSeen(), actualResult.getPlaceSeen());

                assertThat(actualResult, hasProperty("shape"));
                assertEquals(expectedUfoResults.get(index).getShape(), actualResult.getShape());

                assertThat(actualResult, hasProperty("duration"));
                assertEquals(expectedUfoResults.get(index).getDuration(), actualResult.getDuration());

                assertThat(actualResult, hasProperty("description"));
                assertEquals(expectedUfoResults.get(index).getDescription(), actualResult.getDescription());

                index++;
            }
        }
    }

    @Test
    public void searchUfoByMonthYearTest() {
        //When
        List<UfoSighting> actualSightingsForOct1995 = SUT.search(1995, 10);

        //Then
        assertEquals("Oct 1995 had 9 UFO sights!", 8, actualSightingsForOct1995.size());
    }

    @Test
    public void searchWhenNoUfoOnMonthYearTest() {
        //When
        List<UfoSighting> actualSightingsForOct2017 = SUT.search(2017, 10);

        //Then
        assertEquals("Oct 2017 had no UFO sights!", 0, actualSightingsForOct2017.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchUfoByInvalidMonthYearTest() {
        //When
        SUT.search(1995, 14);
    }

    private void countUfo() {
        try {
            UFO_COUNT = Files.lines(Paths.get(TEST_UFO_DATA)).count();
        } catch (IOException e) {
            throw new RuntimeException("Test file not found", e);
        }
    }

    private void getExpectedUfoSightingItems() {
        expectedUfoResults = new ArrayList<>();

        try {
            expectedUfoResults.addAll(Files.lines(Paths.get(TEST_UFO_DATA)).map(line -> {
                String[] lineToken = line.split("\t");
                return new UfoSighting(lineToken[0].trim(), lineToken[1].trim(), lineToken[2].trim(), lineToken[3].trim(), lineToken[4].trim(), lineToken[5].trim());
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException("Test file not found", e);
        }
    }
}
