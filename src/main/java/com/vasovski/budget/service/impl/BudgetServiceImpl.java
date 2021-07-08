package com.vasovski.budget.service.impl;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.model.Expense;
import com.vasovski.budget.repository.BudgetRepository;
import com.vasovski.budget.repository.ExpenseRepository;
import com.vasovski.budget.service.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BudgetServiceImpl implements BudgetService {

//    @Value("${dayLimit}")
    private final Integer dayLimit = 700;

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    @Override
    public Budget getDayBudget(LocalDate date) {
        return budgetRepository.findByDate(date).orElseGet(() -> createBudget(date));
    }

    @Override
    public List<Budget> getMonthBudget(LocalDate date) {
        List<Budget> budgets = budgetRepository.findAllByDateBetween(date.withDayOfMonth(1),
                date.withDayOfMonth(date.lengthOfMonth()));
        if (budgets.size() == 0){
            this.createBudget(date);
            budgets = budgetRepository.findAllByDateBetween(date.withDayOfMonth(1),
                    date.withDayOfMonth(date.lengthOfMonth()));
        }
        return budgets;
    }

    private Budget createBudget(LocalDate initial){
        List<Budget> monthBudgets = new ArrayList<>();
        Budget dayBudget = null;
        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        for (LocalDate d = initial.withDayOfMonth(1); !d.isAfter(end); d = d.plusDays(1)) {
            Budget budget = new Budget(d, dayLimit * d.getDayOfMonth());
            if (d.equals(initial)){
                dayBudget = budget;
            }
            monthBudgets.add(budget);
        }
        budgetRepository.saveAll(monthBudgets);
        //todo add exception if dayBudget == null
        return dayBudget;
    }

    public List<Budget> updateFromNewExpense(Expense expense){
        List<Budget> allByEndOfMonth = budgetRepository.findAllByEndOfMonth(expense.getDate());
        expense.setBudget(getBudget(allByEndOfMonth, expense.getDate()));
        int oldAmount = 0;
        if (expense.getId() != null){
            oldAmount = expenseRepository.findById(expense.getId()).map(Expense::getAmount).orElse(0);
        }
        for (Budget budget: allByEndOfMonth) {
            budget.setBalance(budget.getBalance() - (expense.getAmount() - oldAmount));
        }
        budgetRepository.saveAll(allByEndOfMonth);
        return allByEndOfMonth;
    }

    @Override
    public List<Budget> updateFromDeletedExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(
                () -> new RuntimeException(String.format("Budget with id %s not found", expenseId)));
        List<Budget> allByEndOfMonth = budgetRepository.findAllByEndOfMonth(expense.getDate());
        for (Budget budget: allByEndOfMonth) {
            budget.setBalance(budget.getBalance() + expense.getAmount());
        }
        budgetRepository.saveAll(allByEndOfMonth);
        return allByEndOfMonth;
    }

    private Budget getBudget(List<Budget> budgets, LocalDate date){
        return budgets.stream().filter(budget -> budget.getDate().equals(date)).findAny().orElseThrow(
                () -> new RuntimeException(String.format("Budget with date %s not found", date))
        );
    }

    private Budget saveBudget(Budget budget) {
        Budget oldBudget = budgetRepository.getOne(budget.getId());
        Integer balanceDifference = oldBudget.getBalance() - budget.getBalance();
        List<Budget> budgets = budgetRepository.findAllByEndOfMonth(budget.getDate().plusDays(1));
        for (Budget day : budgets) {
            day.setBalance(day.getBalance() - balanceDifference);
        }
        budgets.add(budget);
        budgetRepository.saveAll(budgets);
        return budget;
    }
}
