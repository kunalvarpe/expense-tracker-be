package io.kunalvarpe.expensetracker.expense.domain;

import lombok.Builder;

@Builder
public record ExpenseInsight(
        String id,
        double amount,
        String currency,
        String category
) {
}
