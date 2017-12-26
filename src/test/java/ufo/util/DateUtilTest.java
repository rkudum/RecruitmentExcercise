package ufo.util;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

    @Test
    public void testIfValidYearMonth() {
        Assert.assertEquals(true, DateUtil.isValidYearMonth(1998, 10));
    }

    @Test
    public void testIfInValidYearMonth() {
        Assert.assertEquals(false, DateUtil.isValidYearMonth(1998, 20));
    }
}
