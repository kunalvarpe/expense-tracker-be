package io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres;

import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseEntityMapper {

    ExpenseEntity toEntity(Expense expense);

    Expense fromEntity(ExpenseEntity expenseEntity);
}
