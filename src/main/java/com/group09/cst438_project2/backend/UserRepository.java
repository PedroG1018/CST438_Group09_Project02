package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findDistinctByUsernameLike(String username);
    User findDistinctByUserIdLike(Integer userId);
    User deleteDistinctByUserId(Integer userId);
}
