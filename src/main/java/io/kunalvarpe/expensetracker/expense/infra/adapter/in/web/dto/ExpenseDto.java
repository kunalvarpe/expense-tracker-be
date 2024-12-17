package io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.kunalvarpe.expensetracker.expense.domain.Category;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExpenseDto(Integer id,
                         String name,
                         Category category,
                         Double amount,
                         String currency,
                         String createdBy,
                         LocalDateTime createdAt,
                         LocalDateTime modifiedAt) {
}
