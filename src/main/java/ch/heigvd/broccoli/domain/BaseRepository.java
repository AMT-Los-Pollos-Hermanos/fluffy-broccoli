package ch.heigvd.broccoli.domain;

import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

    List<T> findAllByApplication(Application application);
    Optional<T> findByIdAndApplication(Long id, Application application);

}
