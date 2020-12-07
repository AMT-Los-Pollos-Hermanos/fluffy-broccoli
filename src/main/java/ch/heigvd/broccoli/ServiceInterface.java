package ch.heigvd.broccoli;

import ch.heigvd.broccoli.application.Application;

import java.util.List;

public interface ServiceInterface<D, E> {

    List<D> all();

    D one(Long id);

    D add(D entity);

    D update(Long id, D entity);

    D delete(Long id);

    D toDTO(E entity);

    List<D> toDTO(List<E> entity);

    Application app();

}
