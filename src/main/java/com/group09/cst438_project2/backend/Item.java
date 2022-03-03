package com.group09.cst438_project2.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class: Item.java
 * Last Modified: 03/02/2022
 * Description: Item entity class that holds item information
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    private Integer userId;
    private Integer listId;

    private String name;
    private String description;
    private String itemUrl;
    private String imageUrl;

    public Item() {
    }

    public Item(Integer userId, Integer listId, String name, String description, String itemUrl, String imageUrl) {
        this.userId = userId;
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.itemUrl = itemUrl;
        this.imageUrl = imageUrl;
    }

    public Item(Integer itemId, Integer userId, Integer listId, String name, String description, String itemUrl, String imageUrl) {
        this.itemId = itemId;
        this.userId = userId;
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.itemUrl = itemUrl;
        this.imageUrl = imageUrl;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
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

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
