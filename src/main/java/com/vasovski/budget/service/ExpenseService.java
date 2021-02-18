package com.vasovski.budget.service;

import com.vasovski.budget.model.Expense;

public interface ExpenseService {

    public Expense create(Expense expense);

    public Expense get(Long id);

    public void delete(Long id);
}
