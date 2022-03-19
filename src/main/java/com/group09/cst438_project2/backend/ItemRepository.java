package com.group09.cst438_project2.backend;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    Iterable<Item> findItemsByListIdLike(Integer listId);
    Item deleteAllByListId(Integer listId);
}
