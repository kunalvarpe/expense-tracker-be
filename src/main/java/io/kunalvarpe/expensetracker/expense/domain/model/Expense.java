package io.kunalvarpe.expensetracker.expense.domain.model;

import io.kunalvarpe.expensetracker.expense.domain.model.enums.Category;
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
