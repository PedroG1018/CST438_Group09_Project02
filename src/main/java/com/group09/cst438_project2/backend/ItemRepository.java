package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

/**
 * Class: ItemRepository.java
 * Last Modified: 03/23/2022
 * Description: Item repository for making queries to Item table.
 *              Includes some custom Spring Boot queries
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
    Iterable<Item> findItemsByListIdLike(Integer listId);
    Iterable<Item> findItemsByUserIdLike(Integer userId);
    Item findDistinctByItemIdLike(Integer itemId);
}
