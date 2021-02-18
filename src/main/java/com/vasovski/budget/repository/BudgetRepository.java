package com.vasovski.budget.repository;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget findByDate(LocalDate date);

}
