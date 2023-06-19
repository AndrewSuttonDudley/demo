package com.crscreditapi.demo.mapper;

import com.crscreditapi.demo.config.MapStructConfig;
import com.crscreditapi.demo.dto.CreditRequestDto;
import com.crscreditapi.demo.model.CreditRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(
        componentModel = "spring",
        config = MapStructConfig.class
)
public abstract class CreditRequestMapper extends AbstractMapper<CreditRequest, CreditRequestDto> {
}
