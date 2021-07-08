package com.vasovski.budget.repository;

import com.vasovski.budget.model.Budget;

import java.time.LocalDate;
import java.util.List;

public interface BudgetCustomRepository {

    List<Budget> findAllByEndOfMonth(LocalDate date);
}
