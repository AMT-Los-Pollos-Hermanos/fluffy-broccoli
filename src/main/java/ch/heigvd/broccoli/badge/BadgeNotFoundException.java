package ch.heigvd.broccoli.badge;

import java.util.UUID;

class BadgeNotFoundException extends RuntimeException {

    BadgeNotFoundException(Long id) {
        super("Could not find badge " + id);
    }
}
