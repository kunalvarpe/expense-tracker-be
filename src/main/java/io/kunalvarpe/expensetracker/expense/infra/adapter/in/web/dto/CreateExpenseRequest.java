package io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto;

import io.kunalvarpe.expensetracker.expense.domain.model.enums.Category;

public record CreateExpenseRequest(String name,
                                   Category category,
                                   Double amount,
                                   String currency) {
}
