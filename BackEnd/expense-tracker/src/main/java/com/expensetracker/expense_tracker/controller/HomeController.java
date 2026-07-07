package com.expensetracker.expense_tracker.controller;

import com.dto.ExpenseDTO;
import com.expensetracker.expense_tracker.entity.Expense;
import com.expensetracker.expense_tracker.response.ApiResponse;
import com.expensetracker.expense_tracker.service.ExpenseService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/expenses")
public class HomeController {

    @Autowired
    private ExpenseService expenseService;


   @PostMapping
public ApiResponse<ExpenseDTO> saveExpense(
        @Valid @RequestBody ExpenseDTO dto) {

    ExpenseDTO savedExpense = expenseService.saveExpense(dto);

    return new ApiResponse<>(
            true,
            "Expense created successfully",
            savedExpense
    );
}

    @GetMapping
public ApiResponse<List<Expense>> getAllExpenses() {

    List<Expense> expenses = expenseService.getAllExpenses();

    return new ApiResponse<>(
            true,
            "Expenses fetched successfully",
            expenses
    );

}
@PutMapping("/{id}")
public ApiResponse<Expense> updateExpense(
        @PathVariable Integer id,
        @Valid @RequestBody Expense dto) {

    Expense updatedExpense = expenseService.updateExpense(id, dto);

    return new ApiResponse<>(
            true,
            "Expense updated successfully",
            updatedExpense
    );

}

@DeleteMapping("/{id}")
public ApiResponse<String> deleteExpense(
        @PathVariable Integer id) {

    expenseService.deleteExpense(id);

    return new ApiResponse<>(
            true,
            "Expense deleted successfully",
            null
    );

}

}