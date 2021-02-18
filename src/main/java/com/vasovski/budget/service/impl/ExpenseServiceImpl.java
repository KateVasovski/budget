package com.vasovski.budget.service.impl;

import com.vasovski.budget.model.Expense;
import com.vasovski.budget.repository.ExpenseRepository;
import com.vasovski.budget.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public Expense create(Expense expense){
        return expenseRepository.save(expense);
    }

    @Override
    public Expense get(Long id){
        return expenseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void delete(Long id){
        expenseRepository.deleteById(id);
    }
}
