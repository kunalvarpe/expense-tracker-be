package io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ExpenseRepository extends ListCrudRepository<ExpenseEntity, Integer> {

    List<ExpenseEntity> findByCreatedBy(String createdBy);

}
