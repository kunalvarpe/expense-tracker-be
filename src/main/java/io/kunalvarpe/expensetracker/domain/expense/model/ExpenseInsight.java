package io.kunalvarpe.expensetracker.domain.expense.model;

public record ExpenseInsight(
        Double totalExpense,
        Double currentExpense,
        String mostExpenseCategory,
        Double mostExpenseCategoryValue,
        String leastExpenseCategory,
        Double leastExpenseCategoryValue
) {
}
