package io.kunalvarpe.expensetracker.expense.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Expense(Integer id,
                      String name,
                      Category category,
                      Double amount,
                      String currency,
                      String createdBy,
                      LocalDateTime createdAt,
                      LocalDateTime modifiedAt) {
}
