package com.studyassistant.service.impl;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import com.studyassistant.model.Category;
import com.studyassistant.service.CategoryService;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final ObjectMapper objectMapper;

    private List<Map<String, String>> appRules = Collections.emptyList();
    private List<Map<String, String>> titleRules = Collections.emptyList();
    private List<Map<String, String>> urlRules = Collections.emptyList();

    public CategoryServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadRules() throws Exception {
        ClassPathResource resource = new ClassPathResource("category-rules.json");
        try (InputStream in = resource.getInputStream()) {
            Map<String, List<Map<String, String>>> rules = objectMapper.readValue(in, new TypeReference<>() {});
            appRules = rules.getOrDefault("appRules", Collections.emptyList());
            titleRules = rules.getOrDefault("titleRules", Collections.emptyList());
            urlRules = rules.getOrDefault("urlRules", Collections.emptyList());
        }
    }

    @Override
    public Category classify(String app, String title, String url) {
        Category c = matchApp(app);
        if (c != null && c.getCategory() != null && !c.getCategory().isEmpty()) {
            return c;
        }
        c = matchKeyword(titleRules, title);
        if (c != null) return c;
        return matchKeyword(urlRules, url);
    }

    private Category matchApp(String app) {
        if (app == null) return null;
        for (Map<String, String> rule : appRules) {
            if (app.contains(rule.get("app"))) {
                return new Category(rule.get("category"), rule.get("subcategory"));
            }
        }
        return null;
    }

    private Category matchKeyword(List<Map<String, String>> rules, String text) {
        if (text == null) return null;
        for (Map<String, String> rule : rules) {
            if (text.contains(rule.get("keyword"))) {
                return new Category(rule.get("category"), rule.get("subcategory"));
            }
        }
        return null;
    }
}