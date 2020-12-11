package ch.heigvd.broccoli;

import ch.heigvd.broccoli.domain.application.Application;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<D, E> {

    List<D> all();

    D one(Long id);

    D add(D entity);

    D update(Long id, D entity);

    D delete(Long id);

    D toDTO(E entity);

    D toDTO(Optional<E> entity);

    List<D> toDTO(List<E> entity);

    Application app();

}
