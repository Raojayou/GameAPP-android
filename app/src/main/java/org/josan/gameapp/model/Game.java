package org.josan.gameapp.model;

import java.io.Serializable;

public class Game implements Serializable {

    private int id;
    private String name;
    private String image;
    private String platform;
    private String web;
    private String doc;
    private String forums;
    private String error_tracker;
    private String description;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getForums() {
        return forums;
    }

    public void setForums(String forums) {
        this.forums = forums;
    }

    public String getError_tracker() {
        return error_tracker;
    }

    public void setError_tracker(String error_tracker) {
        this.error_tracker = error_tracker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return name;
    }
}
