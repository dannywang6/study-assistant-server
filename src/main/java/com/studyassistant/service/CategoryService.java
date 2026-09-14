package com.studyassistant.service;


import com.studyassistant.model.Category;

public interface CategoryService {

    Category classify(String app, String title, String url);

}
