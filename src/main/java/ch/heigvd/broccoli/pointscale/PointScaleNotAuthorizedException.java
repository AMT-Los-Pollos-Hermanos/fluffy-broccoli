package ch.heigvd.broccoli.pointscale;

import java.util.UUID;

public class PointScaleNotAuthorizedException extends RuntimeException{

    PointScaleNotAuthorizedException(Long id, UUID appID){
        super("Application " + appID + " not authorize to access point-scale " +id);
    }
}
