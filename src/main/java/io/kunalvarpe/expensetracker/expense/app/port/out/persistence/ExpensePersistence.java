package io.kunalvarpe.expensetracker.expense.app.port.out.persistence;

import io.kunalvarpe.expensetracker.expense.domain.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpensePersistence {

    List<Expense> findExpensesByUser(String userId);

    Expense saveExpense(Expense expense);

    Optional<Expense> getExpenseById(Integer id);

    void deleteById(Integer id);

    Expense updateExpense(Expense expense);
}
