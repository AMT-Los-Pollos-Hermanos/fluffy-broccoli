package ch.heigvd.broccoli.badge;

import java.util.UUID;

public class BadgeNotAuthorizedException extends RuntimeException{

    BadgeNotAuthorizedException(Long id, UUID appID){
        super("Application " + appID + " not authorize to access badge " +id);
    }
}
