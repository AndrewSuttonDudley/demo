package com.crscreditapi.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {

    public static Pageable of (Integer page, Integer size, String sortBy, String sortDir) {
        return PageRequest.of(page, size, "ASC".equalsIgnoreCase(sortDir) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    }

    public static PageRequest of(Integer page, Integer size, String sortDir) {
        return PageRequest.of(page, size, "ASC".equalsIgnoreCase(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC);
    }
}
