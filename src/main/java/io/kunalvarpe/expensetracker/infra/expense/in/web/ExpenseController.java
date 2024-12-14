package io.kunalvarpe.expensetracker.infra.expense.in.web;

import java.util.List;

import io.kunalvarpe.expensetracker.app.expense.in.ExpenseTrackerUseCase;
import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import io.kunalvarpe.expensetracker.domain.expense.model.ExpenseInsight;
import io.kunalvarpe.expensetracker.infra.expense.in.web.dto.CreateExpenseRequest;
import io.kunalvarpe.expensetracker.infra.expense.in.web.dto.ExpenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ExpenseInsight expenseInsights(@RequestParam Integer userId) {
        return expenseTrackerUseCase.getUserExpenseInsight(userId);
    }

    @GetMapping("/expenses")
    public List<ExpenseDto> expenses(@RequestParam Integer userId) {
        return expenseTrackerUseCase.findAllUserExpenses(userId)
                .stream()
                .map(expenseMapper::toDto)
                .toList();
    }

    @PostMapping("/expenses")
    public ResponseEntity<String> createExpense(@RequestParam Integer userId,
                                                @RequestBody CreateExpenseRequest createExpenseRequest) {
        Expense expense = expenseMapper.toExpense(createExpenseRequest);
        expenseTrackerUseCase.createExpense(userId, expense);
        return ResponseEntity.ok("Expense added successfully.");
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
