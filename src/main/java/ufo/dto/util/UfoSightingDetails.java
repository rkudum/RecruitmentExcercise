package ufo.dto.util;

import ufo.dto.UfoSighting;

public class UfoSightingDetails {

    public static String getDateSeenYYYYMM(UfoSighting ufoSighting){
        String fulldateSeen = ufoSighting.getDateSeen();
        if(fulldateSeen.length()>=6) {
            return fulldateSeen.substring(0, 6);
        }else{
            return fulldateSeen;
        }
    }
}
