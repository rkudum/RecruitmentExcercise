import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ufo.service.UfoSightingService;
import ufo.service.impl.UfoSightingServiceImpl;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        UfoSightingService service = new UfoSightingServiceImpl();

        LOG.info("Total sightings are {}", service.getAllSightings().stream().count());
        LOG.info("Total number of UFO incidents in Oct 1995 were {}", service.search(1995, 10).size());
    }
}
