package com.vasovski.budget.rest;

import com.vasovski.budget.model.Budget;
import com.vasovski.budget.service.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/day")
    public Budget getDayBudget(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day){
        return budgetService.getDayBudget(day);
    }

    @GetMapping("/month")
    public List<Budget> getMonthBudget(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day){
        return budgetService.getMonthBudget(day);
    }

//    @PostMapping()
//    public Budget save(@RequestBody Budget budget){
//        return budgetService.save(budget);
//    }
}
