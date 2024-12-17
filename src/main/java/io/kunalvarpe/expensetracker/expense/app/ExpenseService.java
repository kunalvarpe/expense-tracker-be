package io.kunalvarpe.expensetracker.expense.app;

import io.kunalvarpe.expensetracker.expense.app.port.in.ExpenseTrackerUseCase;
import io.kunalvarpe.expensetracker.expense.app.port.out.persistence.ExpensePersistence;
import io.kunalvarpe.expensetracker.expense.domain.Expense;
import io.kunalvarpe.expensetracker.expense.domain.ExpenseInsight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService implements ExpenseTrackerUseCase {

    private final ExpensePersistence expensePersistence;

    public ExpenseService(ExpensePersistence expensePersistence) {
        this.expensePersistence = expensePersistence;
    }

    @Override
    public List<ExpenseInsight> getUserExpenseInsight(String userId) {
        List<ExpenseInsight> expenseInsights = new ArrayList<>();
        List<Expense> expenses = expensePersistence.findExpensesByUser(userId);
        Month currentMonth = LocalDate.now().getMonth();

        String currency = expenses.stream().map(Expense::currency).findFirst().orElse("INR");
        Optional<Double> totalExpense = expenses.stream()
                .map(Expense::amount)
                .reduce(Double::sum);
        ExpenseInsight.ExpenseInsightBuilder builder = ExpenseInsight.builder().currency(currency);
        expenseInsights.add(builder.id("totalExpense").amount(totalExpense.orElse(0.0)).build());

        Optional<Double> currentExpense = expenses.stream()
                .filter(expense -> currentMonth == expense.createdAt().getMonth())
                .map(Expense::amount)
                .reduce(Double::sum);
        expenseInsights.add(builder.id("currentExpense").amount(currentExpense.orElse(0.0)).build());

        Optional<Expense> maxExpense = expenses.stream()
                .max(Comparator.comparing(Expense::amount));
        expenseInsights.add(builder.id("mostExpense").amount(maxExpense.map(Expense::amount).orElse(0.0))
                .category(maxExpense.map(Expense::category).map(Enum::toString).orElse("")).build());

        Optional<Expense> minExpense = expenses.stream()
                .min(Comparator.comparing(Expense::amount));
        expenseInsights.add(builder.id("leastExpense").amount(minExpense.map(Expense::amount).orElse(0.0))
                .category(minExpense.map(Expense::category).map(Enum::toString).orElse("")).build());

        return expenseInsights;
    }

    @Override
    public List<Expense> findAllUserExpenses(String userId) {
        return expensePersistence.findExpensesByUser(userId);
    }

    public Expense createExpense(Expense expense) {
        return expensePersistence.saveExpense(expense);
    }

    public Expense findExpense(Integer id) {
        return expensePersistence.getExpenseById(id).orElseThrow();
    }

    public void deleteExpense(Integer id) {
        expensePersistence.deleteById(id);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return expensePersistence.updateExpense(expense);
    }
}
