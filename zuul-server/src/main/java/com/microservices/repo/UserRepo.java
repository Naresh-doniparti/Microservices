package com.microservices.repo;

import com.microservices.model.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    @AllowFiltering
    User findByEmail(String email);
}
