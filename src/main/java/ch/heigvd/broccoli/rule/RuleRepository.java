package ch.heigvd.broccoli.rule;


import ch.heigvd.broccoli.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByApplication(Application application);

    Optional<Rule> findByIdAndApplication(Long id, Application application);

}
