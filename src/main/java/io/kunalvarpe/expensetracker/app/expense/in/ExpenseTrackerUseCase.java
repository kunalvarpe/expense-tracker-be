package io.kunalvarpe.expensetracker.app.expense.in;

import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import io.kunalvarpe.expensetracker.domain.expense.model.ExpenseInsight;

import java.util.List;

public interface ExpenseTrackerUseCase {
    ExpenseInsight getUserExpenseInsight(Integer userId);

    List<Expense> findAllUserExpenses(Integer userId);

    void createExpense(Integer userId, Expense expense);

    Expense findExpense(Integer id);

    void deleteExpense(Integer id);
}
