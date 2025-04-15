package com.edstry.Tasklist.web.mappers;


import org.mapstruct.Mapping;

import java.util.List;

public interface Mappable<E, D> {
    D toDto(E entity);
    List<D> toDto(List<E> entity);
    E toEntity(D dto);
    List<E> toEntity(List<D> dtos);
}
