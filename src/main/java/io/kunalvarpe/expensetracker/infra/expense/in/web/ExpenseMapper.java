package io.kunalvarpe.expensetracker.infra.expense.in.web;

import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import io.kunalvarpe.expensetracker.infra.expense.in.web.dto.CreateExpenseRequest;
import io.kunalvarpe.expensetracker.infra.expense.in.web.dto.ExpenseDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

/**
 * @author Kunal Varpe
 */
@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {

    ExpenseDto toDto(Expense expense);

    Expense toExpense(CreateExpenseRequest createExpenseRequest);
}
