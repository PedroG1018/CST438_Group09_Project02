package com.group09.cst438_project2.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class: WishList.java
 * Last Modified: 03/23/2022
 * Description: Wish list entity for wish_list table
 */
@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer listId;
    private Integer userId;

    private String listName;
    private String description;

    public WishList(){

    }

    public WishList(Integer userId, String listName, String description){
        this.userId = userId;
        this.listName = listName;
        this.description = description;
    }

    public WishList(Integer listId, Integer userId, String listName, String description){
        this.listId = listId;
        this.userId = userId;
        this.listName = listName;
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
