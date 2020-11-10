package ch.heigvd.broccoli.badge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class BadgeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BadgeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String badgeNotFoundHandler(BadgeNotFoundException e) {
        return e.getMessage();
    }

}
