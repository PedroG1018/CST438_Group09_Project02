package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

public interface WishListRepository extends CrudRepository<WishList, Integer> {
    WishList findDistinctByListIdLike(Integer listId);
    Iterable<WishList> findWishListsByUserIdLike(Integer userId);
}
