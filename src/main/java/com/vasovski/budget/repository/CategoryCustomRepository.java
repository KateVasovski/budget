package com.vasovski.budget.repository;

import com.vasovski.budget.model.Category;

public interface CategoryCustomRepository {

    Category getCategoryWithParent(Long id);
}
