package kz.pet.spliff.domain.dto;

import kz.pet.spliff.domain.enam.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AppUserDTO {
    private Long id;
    private String username;
    private String name;
    private Set<Role> roles;
}
