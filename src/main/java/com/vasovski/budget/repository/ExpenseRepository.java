package com.vasovski.budget.repository;

import com.vasovski.budget.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> getAllByDateBetween(LocalDate start, LocalDate end);

}
