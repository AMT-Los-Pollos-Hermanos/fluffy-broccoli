package ch.heigvd.broccoli.application.badge;

import java.util.UUID;

class BadgeNotAuthorizedException extends RuntimeException{

    BadgeNotAuthorizedException(Long id, UUID appID){
        super("Application " + appID + " not authorize to access badge " +id);
    }
}
