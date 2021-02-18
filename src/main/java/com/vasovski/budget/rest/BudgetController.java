package com.vasovski.budget.rest;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.service.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/day")
    public Budget getDayBudget(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day){
        return budgetService.getDayBudget(day);
    }
}
