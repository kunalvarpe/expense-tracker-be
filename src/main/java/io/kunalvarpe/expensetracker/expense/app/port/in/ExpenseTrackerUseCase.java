package io.kunalvarpe.expensetracker.expense.app.port.in;

import io.kunalvarpe.expensetracker.expense.domain.Expense;
import io.kunalvarpe.expensetracker.expense.domain.ExpenseInsight;

import java.util.List;

public interface ExpenseTrackerUseCase {
    List<ExpenseInsight> getUserExpenseInsight(String userId);

    List<Expense> findAllUserExpenses(String userId);

    Expense createExpense(Expense expense);

    Expense findExpense(Integer id);

    void deleteExpense(Integer id);

    Expense updateExpense(Expense expense);
}
