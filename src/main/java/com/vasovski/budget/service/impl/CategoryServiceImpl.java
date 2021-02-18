package com.vasovski.budget.service.impl;

import com.vasovski.budget.model.Category;
import com.vasovski.budget.repository.CategoryRepository;
import com.vasovski.budget.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}