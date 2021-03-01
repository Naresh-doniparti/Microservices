package com.microservices.repository;

import com.microservices.model.TestTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TestTableRepository extends CrudRepository<TestTable, Serializable> {
}
