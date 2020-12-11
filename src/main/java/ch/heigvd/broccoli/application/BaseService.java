package ch.heigvd.broccoli.application;

import ch.heigvd.broccoli.ServiceInterface;
import ch.heigvd.broccoli.domain.BaseRepository;
import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService<D, E> implements ServiceInterface<D, E> {

    protected BaseRepository<E> repository;

    @Override
    public List<D> all() {
        return toDTO(repository.findAllByApplication(app()));
    }

    @Override
    public D one(Long id) {
        return toDTO(repository.findByIdAndApplication(id, app()));
    }

    @Override
    public D delete(Long id) {
        return toDTO(repository.findByIdAndApplication(id, app()).map(entity -> {
            repository.delete(entity);
            return entity;
        }).orElseThrow(() -> new RuntimeException("Not found")));
    }

    @Override
    public Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public D toDTO(Optional<E> entity) {
        return entity.map(this::toDTO).orElse(null);
    }

    @Override
    public List<D> toDTO(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
