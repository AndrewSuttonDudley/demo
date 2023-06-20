package com.crscreditapi.demo.mapper;

import com.crscreditapi.demo.config.MapStructConfig;
import com.crscreditapi.demo.dto.APIRequestDto;
import com.crscreditapi.demo.model.mongo.APIRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(
        componentModel = "spring",
        config = MapStructConfig.class
)
public abstract class APIRequestMapper extends AbstractMapper<APIRequest, APIRequestDto> {
}
