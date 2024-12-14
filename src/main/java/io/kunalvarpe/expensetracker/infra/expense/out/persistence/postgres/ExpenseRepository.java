package io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres;

import java.util.List;

import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import org.springframework.data.repository.ListCrudRepository;

public interface ExpenseRepository extends ListCrudRepository<ExpenseEntity, Integer> {

    List<ExpenseEntity> findByUserId(Integer userId);

}
