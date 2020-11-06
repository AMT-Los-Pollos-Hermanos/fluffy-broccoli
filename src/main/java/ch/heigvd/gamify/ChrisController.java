package ch.heigvd.gamify;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChrisController {

    @GetMapping("/chris")
    public String coucouChris() {
        return "Coucou Chris";
    }

}
