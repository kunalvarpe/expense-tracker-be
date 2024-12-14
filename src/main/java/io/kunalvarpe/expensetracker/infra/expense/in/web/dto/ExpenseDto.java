package io.kunalvarpe.expensetracker.infra.expense.in.web.dto;

import io.kunalvarpe.expensetracker.domain.expense.model.Category;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExpenseDto(Integer id,
                         String name,
                         Category category,
                         Double amount,
                         String currency,
                         Integer userId,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
}
