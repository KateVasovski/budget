package com.vasovski.budget.service;

import com.vasovski.budget.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    Category get(Long id);

    List<Category> getAll();

    void delete(Long id);
}
