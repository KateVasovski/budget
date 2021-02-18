package com.vasovski.budget.service;

import com.vasovski.budget.model.Budget;

import java.time.LocalDate;

public interface BudgetService {

    Budget getDayBudget(LocalDate date);
}
