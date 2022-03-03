package com.group09.cst438_project2.backend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Class: UserRepository.java
 * Last Modified: 03/01/2022
 * Description: Queries to get data from User table
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    @Query(value = "SELECT * FROM User u WHERE u.name LIKE %:name%",
    countQuery = "SELECT count(*) FROM User",
            nativeQuery = true)
    List<User> findUserByName(
            @Param("name") String name
    );
}