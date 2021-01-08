package ch.heigvd.broccoli.domain.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PagingSortingUserRepository extends PagingAndSortingRepository<UserEntity, UUID> {

}
