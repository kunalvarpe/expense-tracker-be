package io.kunalvarpe.expensetracker.expense.domain.model;

import lombok.Builder;

@Builder
public record ExpenseInsight(
        String id,
        double amount,
        String currency,
        String category
) {
}
