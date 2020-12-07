package ch.heigvd.broccoli.application.pointscale;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PointScaleNotFoundAdvice {

    @ExceptionHandler(PointScaleNotFoundException.class)
    ResponseEntity<?> badgeNotFoundHandler(PointScaleNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
    }

    @ExceptionHandler(PointScaleNotAuthorizedException.class)
    ResponseEntity<?> BadgeNotAuthorizedHandler(PointScaleNotAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
    }

}
