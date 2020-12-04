package ch.heigvd.broccoli.rule;


import ch.heigvd.broccoli.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByApplication(Application application);

}
