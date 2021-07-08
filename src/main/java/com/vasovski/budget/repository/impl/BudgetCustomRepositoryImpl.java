package com.vasovski.budget.repository.impl;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.repository.BudgetCustomRepository;
import com.vasovski.budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.List;

public class BudgetCustomRepositoryImpl implements BudgetCustomRepository {

    @Autowired
    @Lazy
    BudgetRepository budgetRepository;

    @Override
    public List<Budget> findAllByEndOfMonth(LocalDate date) {
        LocalDate end = date.withDayOfMonth(date.lengthOfMonth());
        return budgetRepository.findAllByDateBetween(date, end);
    }
}
