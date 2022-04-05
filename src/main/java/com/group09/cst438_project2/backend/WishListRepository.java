package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

/**
 * Class: WishListRepository.java
 * Last Modified: 03/23/2022
 * Description: Wish list repository for making queries to wish_list table.
 *              Includes custom Spring Boot queries.
 */
public interface WishListRepository extends CrudRepository<WishList, Integer> {
    WishList findDistinctByListIdLike(Integer listId);
    Iterable<WishList> findWishListsByUserIdLike(Integer userId);
}
