package com.vasovski.budget.service;

import com.vasovski.budget.model.Category;

public interface CategoryService {

    public Category create(Category category);

    public Category get(Long id);

    public void delete(Long id);
}
