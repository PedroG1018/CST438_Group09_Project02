package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

/**
 * Class: UserRepository.java
 * Last Modified: 03/23/2022
 * Description: User repository for making queries to user table.
 *              Includes custom Spring Boot queries.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findDistinctByUsernameLike(String username);
    User findDistinctByUserIdLike(Integer userId);
}
