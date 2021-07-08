package com.vasovski.budget.repository;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>, BudgetCustomRepository {

    Optional<Budget> findByDate(LocalDate date);

    List<Budget> findAllByDateBetween(LocalDate start, LocalDate end);
}
