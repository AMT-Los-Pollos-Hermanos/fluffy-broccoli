package ch.heigvd.broccoli.application.badge;

class BadgeNotFoundException extends RuntimeException {

    BadgeNotFoundException(Long id) {
        super("Could not find badge " + id);
    }
}
