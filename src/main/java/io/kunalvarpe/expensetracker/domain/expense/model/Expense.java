package io.kunalvarpe.expensetracker.domain.expense.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Expense(Integer id,
                      String name,
                      Category category,
                      Double amount,
                      String currency,
                      Integer userId,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
}
