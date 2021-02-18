package com.vasovski.budget.rest;

import com.vasovski.budget.model.Category;
import com.vasovski.budget.model.Expense;
import com.vasovski.budget.repository.CategoryRepository;
import com.vasovski.budget.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id){
        return categoryService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

}
