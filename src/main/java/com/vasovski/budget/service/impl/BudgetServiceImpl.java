package com.vasovski.budget.service.impl;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.model.Expense;
import com.vasovski.budget.repository.BudgetRepository;
import com.vasovski.budget.repository.ExpenseRepository;
import com.vasovski.budget.service.BudgetService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService {

//    @Value("${dayLimit}")
    private final Integer dayLimit = 700;

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    @Override
    public Budget getDayBudget(LocalDate date) {
        return budgetRepository.findByDate(date);
    }
}
