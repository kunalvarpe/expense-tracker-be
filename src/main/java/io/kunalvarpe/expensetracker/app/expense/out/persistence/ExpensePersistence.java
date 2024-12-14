package io.kunalvarpe.expensetracker.app.expense.out.persistence;

import io.kunalvarpe.expensetracker.domain.expense.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpensePersistence {

    List<Expense> findExpensesByUser(Integer userId);

    Expense saveExpense(Expense expense);

    Optional<Expense> getExpenseById(Integer id);

    void deleteById(Integer id);
}
