package com.crscreditapi.demo.mapper;

import com.crscreditapi.demo.dto.AbstractDto;
import com.crscreditapi.demo.model.BaseModel;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractMapper<ENTITY extends BaseModel, DTO extends AbstractDto> {

    public abstract ENTITY map(DTO dto);

    public abstract DTO map(ENTITY entity);

    public List<DTO> list(Iterable<ENTITY> entities) {
        return stream(entities)
                .collect(Collectors.toList());
    }

    public Page<DTO> page(Page<ENTITY> entities) {
        if (entities == null) {
            return null;
        }
        return new PageImpl<>(
                list(entities),
                entities.getPageable(),
                entities.getTotalElements()
        );
    }

    public Set<DTO> set(Iterable<ENTITY> entities) {
        return stream(entities)
                .collect(Collectors.toSet());
    }

    private Stream<DTO> stream(Iterable<ENTITY> entities) {
        if (entities == null) {
            return null;
        }
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::map);
    }

    public abstract void update(DTO from, @MappingTarget ENTITY to);

    public abstract void update(ENTITY from, @MappingTarget DTO to);
}
