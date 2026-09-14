package com.studyassistant.model;


import lombok.Data;

@Data
public class Category {
    private String category;
    private String subcategory;

    public Category() {

    }

    public Category(String category, String subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }
}
