package com.vasovski.budget.service;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService {

    Budget getDayBudget(LocalDate date);

    List<Budget> getMonthBudget(LocalDate date);

    List<Budget> updateFromNewExpense(Expense expense);

    List<Budget> updateFromDeletedExpense(Long expenseId);
}
