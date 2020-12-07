package ch.heigvd.broccoli.domain.rule;


import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByApplication(Application application);

    Optional<Rule> findByIdAndApplication(Long id, Application application);

}
