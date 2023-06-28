package com.crscreditapi.demo.mapper;

import com.crscreditapi.demo.config.MapStructConfig;
import com.crscreditapi.demo.dto.CRSRequestDto;
import com.crscreditapi.demo.model.mongo.CRSRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(
        componentModel = "spring",
        config = MapStructConfig.class
)
public abstract class CRSRequestMapper extends AbstractMapper<CRSRequest, CRSRequestDto> {
}
