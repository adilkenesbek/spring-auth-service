package kz.pet.agato.domain;

import kz.pet.agato.domain.enam.Role;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role name;
}
