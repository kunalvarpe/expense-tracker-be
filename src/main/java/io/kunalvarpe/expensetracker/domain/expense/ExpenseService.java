package io.kunalvarpe.expensetracker.domain.expense;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import io.kunalvarpe.expensetracker.app.expense.in.ExpenseTrackerUseCase;
import io.kunalvarpe.expensetracker.app.expense.out.persistence.ExpensePersistence;
import io.kunalvarpe.expensetracker.domain.expense.model.Category;
import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import io.kunalvarpe.expensetracker.domain.expense.model.ExpenseInsight;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService implements ExpenseTrackerUseCase {

    private final ExpensePersistence expensePersistence;

    public ExpenseService(ExpensePersistence expensePersistence) {
        this.expensePersistence = expensePersistence;
    }

    @Override
    public ExpenseInsight getUserExpenseInsight(Integer userId) {
        List<Expense> expenses = expensePersistence.findExpensesByUser(userId);
        Month currentMonth = LocalDate.now().getMonth();

        Optional<Double> totalExpense = expenses.stream()
                .map(Expense::amount)
                .reduce(Double::sum);
        Optional<Double> currentExpense = expenses.stream()
                .filter(expense -> currentMonth == expense.createdAt().getMonth())
                .map(Expense::amount)
                .reduce(Double::sum);
        Optional<Expense> maxExpense = expenses.stream()
                .max(Comparator.comparing(Expense::amount));
        Optional<Expense> minExpense = expenses.stream()
                .min(Comparator.comparing(Expense::amount));

        return new ExpenseInsight(
                totalExpense.orElse(0.0),
                currentExpense.orElse(0.0),
                maxExpense.map(Expense::category).map(Category::name).orElse(null),
                maxExpense.map(Expense::amount).orElse(0.0),
                minExpense.map(Expense::category).map(Category::name).orElse(null),
                minExpense.map(Expense::amount).orElse(0.0)
        );
    }

    @Override
    public List<Expense> findAllUserExpenses(Integer userId) {
        return expensePersistence.findExpensesByUser(userId);
    }

    public void createExpense(Integer userId, Expense expense) {
        expensePersistence.saveExpense(expense);
    }

    public Expense findExpense(Integer id) {
        return expensePersistence.getExpenseById(id).orElseThrow();
    }

    public void deleteExpense(Integer id) {
        expensePersistence.deleteById(id);
    }
}
