package ufo.dto.util;

import org.junit.Assert;
import org.junit.Test;
import ufo.dto.UfoSighting;

public class UfoSightingDetailsTest {

    @Test
    public void getDateSeenYYYYMM() {

        Assert.assertEquals("201010", UfoSightingDetails.getDateSeenYYYYMM(new UfoSighting("20101001", "20101001",
                "NY", "circle", "10 s", "Yet another sighting")));

        Assert.assertEquals("201010", UfoSightingDetails.getDateSeenYYYYMM(new UfoSighting("201010", "20101001",
                "NY", "circle", "10 s", "Yet another sighting")));

        Assert.assertEquals("2010", UfoSightingDetails.getDateSeenYYYYMM(new UfoSighting("2010", "20101001",
                "NY", "circle", "10 s", "Yet another sighting")));

        Assert.assertNull(UfoSightingDetails.getDateSeenYYYYMM(null));
    }
}