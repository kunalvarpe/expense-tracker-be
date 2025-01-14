package io.kunalvarpe.expensetracker.expense.domain.port.in;

import io.kunalvarpe.expensetracker.expense.domain.model.Expense;
import io.kunalvarpe.expensetracker.expense.domain.model.ExpenseInsight;

import java.util.List;

public interface ExpenseTrackerUseCase {
    List<ExpenseInsight> getUserExpenseInsight(String userId);

    List<Expense> findAllUserExpenses(String userId);

    Expense createExpense(Expense expense);

    Expense findExpense(Integer id);

    void deleteExpense(Integer id);

    Expense updateExpense(Expense expense);
}
