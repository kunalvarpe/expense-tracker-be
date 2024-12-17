package io.kunalvarpe.expensetracker.expense.infra.adapter.in.web;

import io.kunalvarpe.expensetracker.expense.domain.Expense;
import io.kunalvarpe.expensetracker.expense.infra.shared.LocalDateTimeMapper;
import io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto.CreateExpenseRequest;
import io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto.ExpenseDto;
import io.kunalvarpe.expensetracker.expense.infra.adapter.in.web.dto.UpdateExpenseRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

/**
 * @author Kunal Varpe
 */
@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {LocalDateTimeMapper.class})
public interface ExpenseMapper {

    ExpenseDto toDto(Expense expense);

    @Mapping(target = "createdBy", source = "userId")
    Expense toExpense(CreateExpenseRequest createExpenseRequest, String userId);

    @Mapping(target = "id", source = "expenseId")
    Expense toExpense(UpdateExpenseRequest updateExpenseRequest, Integer expenseId);
}
