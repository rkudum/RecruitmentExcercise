package ufo;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ufo.dto.UfoSighting;
import ufo.service.UfoSightingService;
import ufo.service.impl.UfoSightingServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UfoSightingServiceTest {

    private UfoSightingService SUT;

    private final String TEST_UFO_DATA = UfoSightingServiceTest.class.getResource("/ufo_awesome_test_data.tsv").getPath();

    private long UFO_COUNT = 0;

    @Before
    public void setup() {
        SUT = new UfoSightingServiceImpl(TEST_UFO_DATA);
    }

    @Test
    public void getAllUfoSightingsTest() {
        //Given
        countUfo();

        //When
        List<UfoSighting> actualSightings = SUT.getAllSightings();

        //Then
        assertThat(actualSightings, hasSize((int) UFO_COUNT));

        if (actualSightings.size() > 0) {
            AtomicInteger counter = new AtomicInteger(0);
            getExpectedUfoSightingItems().stream().forEach(ufoSigh ->
                    assertEquals(actualSightings.get(counter.getAndIncrement()), ufoSigh)
            );
        }
    }

    @Test
    @Ignore
    public void searchUfoByMonthYearTest() {

    }

    private void countUfo() {
        try {
            UFO_COUNT = Files.lines(Paths.get(TEST_UFO_DATA)).count();
        } catch (IOException e) {
            throw new RuntimeException("Test file not found", e);
        }
    }

    private List<UfoSighting> getExpectedUfoSightingItems() {
        try {
            return Files.lines(Paths.get(TEST_UFO_DATA)).map(line -> {
                String[] lineToken = line.split("\t");
                return new UfoSighting(lineToken[0].trim(), lineToken[1].trim(), lineToken[2].trim(), lineToken[3].trim(), lineToken[4].trim(), lineToken[5].trim());
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Test file not found", e);
        }
    }
}
