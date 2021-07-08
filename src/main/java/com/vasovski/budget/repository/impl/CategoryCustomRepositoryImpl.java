package com.vasovski.budget.repository.impl;

import com.vasovski.budget.model.Category;
import com.vasovski.budget.repository.CategoryCustomRepository;
import com.vasovski.budget.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.NoSuchElementException;

public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    @Autowired
    @Lazy
    CategoryRepository categoryRepository;

    @Override
    public Category getCategoryWithParent(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        String[] treePath = category.getTree().split("\\.");
        if (treePath.length >= 2){
            Category parent = categoryRepository.getByTreeEnd(treePath[treePath.length - 2]);
            category.setParent(parent);
        }
        return category;
    }
}
