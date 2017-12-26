import ufo.service.UfoSightingService;
import ufo.service.impl.UfoSightingServiceImpl;

public class Main {

    public static void main(String[] args) {
        UfoSightingService service = new UfoSightingServiceImpl();

        System.out.println("Total sightings are " +service.getAllSightings().stream().count());

        System.out.println(String.format("Total number of UFO incidents in Oct 1995 were %d", service.search(1995, 10).size()));

    }
}
