package com.vasovski.budget.service.impl;

import com.vasovski.budget.model.Expense;
import com.vasovski.budget.repository.ExpenseRepository;
import com.vasovski.budget.service.BudgetService;
import com.vasovski.budget.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BudgetService budgetService;

    @Override
    @Transactional
    public Expense create(Expense expense){
        budgetService.updateFromNewExpense(expense);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense get(Long id){
        return expenseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void delete(Long id){
        budgetService.updateFromDeletedExpense(id);
        expenseRepository.deleteById(id);
    }
}
