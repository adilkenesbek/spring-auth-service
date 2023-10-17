package com.example.authservice.domain.mapper;

import java.util.List;

public interface EntityMapper <E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    List<D> toDto(List<E> entityList);
    List<E> toEntity(List<D> dtoList);

}
