package io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto;

import io.kunalvarpe.expensetracker.expense.domain.Category;

public record UpdateExpenseRequest(String name,
                                   Category category,
                                   Double amount) {
}
