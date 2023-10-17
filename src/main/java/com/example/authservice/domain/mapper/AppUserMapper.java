package com.example.authservice.domain.mapper;

import com.example.authservice.domain.AppUserEntity;
import com.example.authservice.domain.RoleEntity;
import com.example.authservice.domain.dto.AppUserDTO;
import com.example.authservice.domain.enam.Role;
import com.example.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppUserMapper implements EntityMapper<AppUserEntity, AppUserDTO> {

    private final RoleRepository roleRepository;

    @Override
    public AppUserDTO toDto(AppUserEntity entity) {
        AppUserDTO dto = new AppUserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        Set<Role> roles = entity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet());
        dto.setRoles(roles);
        return dto;
    }

    @Override
    public AppUserEntity toEntity(AppUserDTO dto) {
        AppUserEntity entity = new AppUserEntity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        Set<RoleEntity> roles = roleRepository.findAllByNameIn(dto.getRoles());
        entity.setRoles(roles);
        return entity;
    }

    @Override
    public List<AppUserDTO> toDto(List<AppUserEntity> entityList) {
        List<AppUserDTO> dtoList = new ArrayList<>();

        for (AppUserEntity entity: entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @Override
    public List<AppUserEntity> toEntity(List<AppUserDTO> dtoList) {
        //problem: unnecessary role sql-requests to db
        return null;
    }
}
