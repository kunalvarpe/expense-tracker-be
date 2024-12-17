package io.kunalvarpe.expensetracker.expense.infra.adapter.in.web;

import io.kunalvarpe.expensetracker.expense.app.port.in.ExpenseTrackerUseCase;
import io.kunalvarpe.expensetracker.expense.domain.Expense;
import io.kunalvarpe.expensetracker.expense.domain.ExpenseInsight;
import io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto.CreateExpenseRequest;
import io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto.ExpenseDto;
import io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto.UpdateExpenseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseTrackerUseCase expenseTrackerUseCase;
    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpenseTrackerUseCase expenseTrackerUseCase, ExpenseMapper expenseMapper) {
        this.expenseTrackerUseCase = expenseTrackerUseCase;
        this.expenseMapper = expenseMapper;
    }

    @GetMapping("/insights")
    public List<ExpenseInsight> expenseInsights(@RequestParam String userId) {
        return expenseTrackerUseCase.getUserExpenseInsight(userId);
    }

    @GetMapping("/expenses")
    public List<ExpenseDto> expenses(@RequestParam String userId) {
        return expenseTrackerUseCase.findAllUserExpenses(userId)
                .stream()
                .map(expenseMapper::toDto)
                .toList();
    }

    @PostMapping("/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseDto createExpense(@RequestParam String userId,
                                    @RequestBody CreateExpenseRequest createExpenseRequest) {
        Expense expense = expenseMapper.toExpense(createExpenseRequest, userId);
        Expense newExpense = expenseTrackerUseCase.createExpense(expense);
        return expenseMapper.toDto(newExpense);
    }

    @PatchMapping("/expenses/{id}")
    public ExpenseDto updateExpense(@PathVariable Integer id,
                                    @RequestBody UpdateExpenseRequest updateExpenseRequest) {
        Expense expense = expenseMapper.toExpense(updateExpenseRequest, id);
        return expenseMapper.toDto(expenseTrackerUseCase.updateExpense(expense));
    }

    @GetMapping("/expenses/{id}")
    public ExpenseDto getExpense(@PathVariable Integer id) {
        return expenseMapper.toDto(expenseTrackerUseCase.findExpense(id));
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer id) {
        expenseTrackerUseCase.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
