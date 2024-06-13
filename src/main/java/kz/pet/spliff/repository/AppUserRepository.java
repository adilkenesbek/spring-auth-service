package kz.pet.agato.repository;

import kz.pet.agato.domain.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByEmail(String email);
}
