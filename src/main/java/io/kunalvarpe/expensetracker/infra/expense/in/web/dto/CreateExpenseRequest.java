package io.kunalvarpe.expensetracker.infra.expense.in.web.dto;

import io.kunalvarpe.expensetracker.domain.expense.model.Category;

import java.time.LocalDateTime;

public record CreateExpenseRequest(String name,
                                   Category category,
                                   Double amount,
                                   String currency,
                                   Integer userId) {
}
