package com.crscreditapi.demo.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T, ID> extends ListCrudRepository<T, ID>, ListPagingAndSortingRepository<T, ID> {
}
