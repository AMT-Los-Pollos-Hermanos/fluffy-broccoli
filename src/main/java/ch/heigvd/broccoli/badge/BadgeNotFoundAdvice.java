package ch.heigvd.broccoli.badge;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class BadgeNotFoundAdvice {

    @ExceptionHandler(BadgeNotFoundException.class)
    ResponseEntity<?> badgeNotFoundHandler(BadgeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
    }

}
