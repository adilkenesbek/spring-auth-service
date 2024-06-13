package kz.pet.spliff.repository;

import kz.pet.spliff.domain.RoleEntity;
import kz.pet.spliff.domain.enam.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(Role name);
    Set<RoleEntity> findAllByNameIn(Set<Role> roles);
}
