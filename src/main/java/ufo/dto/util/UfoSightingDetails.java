package ufo.dto.util;

import ufo.dto.UfoSighting;

public class UfoSightingDetails {

    public static String getDateSeenYYYYMM(UfoSighting ufoSighting){
        String yearAndMonth = null;

        if(null != ufoSighting) {
            String fulldateSeen = ufoSighting.getDateSeen();
            if (fulldateSeen.length() >= 6) {
                yearAndMonth =  fulldateSeen.substring(0, 6);
            } else {
                yearAndMonth = fulldateSeen;
            }
        }
        return yearAndMonth;
    }
}
