package com.anhquanha.passkeeper.model;

/**
 * Created by anhquan.ha on 3/7/2018.
 */

public class Account {
    String id;
    String loginId;
    String password;
    String category;
    String ownerId;
    String createdAt;

    public Account(String id, String loginId, String password, String category, String ownerId, String createdAt) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.category = category;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
