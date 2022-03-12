package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

public interface WishListRepository extends CrudRepository<WishList, Integer> {
    WishList findDistinctByUserIdAndAndListIdLike(Integer userId, Integer listId);

    Iterable<WishList> findWishListsByUserIdLike(Integer userId);

}
