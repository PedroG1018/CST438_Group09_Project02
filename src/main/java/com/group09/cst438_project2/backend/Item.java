package com.group09.cst438_project2.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    private Integer listId;
    private Integer userId;

    private String itemName;
    private String itemURL;
    private String imgURL;
    private String description;

    public Item(){

    }

    public Item(Integer userId, Integer listId, String itemName, String itemURL, String imgURL, String description){
        this.userId = userId;
        this.listId = listId;
        this.itemName = itemName;
        this.itemURL = itemURL;
        this.imgURL = imgURL;
        this.description = description;
    }

    public Item(Integer itemId, Integer userId, Integer listId, String itemName, String itemURL, String imgURL, String description){
        this.itemId = itemId;
        this.userId = userId;
        this.listId = listId;
        this.itemName = itemName;
        this.itemURL = itemURL;
        this.imgURL = imgURL;
        this.description = description;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
