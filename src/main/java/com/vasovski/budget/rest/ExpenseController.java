package com.vasovski.budget.rest;

import com.vasovski.budget.model.Expense;
import com.vasovski.budget.service.impl.ExpenseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseServiceImpl expenseService;

    @PostMapping()
    public Expense create(@RequestBody Expense expense){
        return expenseService.create(expense);
    }

    @GetMapping("/{id}")
    public Expense get(@PathVariable Long id){
        return expenseService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        expenseService.delete(id);
    }
}
