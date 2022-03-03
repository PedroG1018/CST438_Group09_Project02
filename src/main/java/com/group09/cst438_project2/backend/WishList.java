package com.group09.cst438_project2.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class: Wishlist.java
 * Last Modified: 03/02/2022
 * Description: Wishlist entity class that holds wishlist information
 */
@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer listId;

    private Integer userId;

    private String name;
    private String description;

    public WishList() {

    }

    public WishList(Integer userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public WishList(Integer listId, Integer userId, String name, String description) {
        this.listId = listId;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
