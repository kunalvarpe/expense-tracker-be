package io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres;

import io.kunalvarpe.expensetracker.expense.domain.Expense;
import io.kunalvarpe.expensetracker.expense.infra.shared.LocalDateTimeMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {LocalDateTimeMapper.class})
public interface ExpenseEntityMapper {

    ExpenseEntity toEntity(Expense expense);

    Expense fromEntity(ExpenseEntity expenseEntity);

    void merge(Expense expense, @MappingTarget ExpenseEntity expenseEntity);
}
