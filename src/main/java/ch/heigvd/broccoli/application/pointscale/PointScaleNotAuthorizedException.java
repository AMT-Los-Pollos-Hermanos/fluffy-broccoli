package ch.heigvd.broccoli.application.pointscale;

import java.util.UUID;

public class PointScaleNotAuthorizedException extends RuntimeException{

    public PointScaleNotAuthorizedException(Long id, UUID appID){
        super("Application " + appID + " not authorize to access point-scale " +id);
    }
}
