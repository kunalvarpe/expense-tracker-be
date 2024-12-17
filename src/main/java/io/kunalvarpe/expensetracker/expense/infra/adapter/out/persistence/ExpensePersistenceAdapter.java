package io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence;

import io.kunalvarpe.expensetracker.expense.app.port.out.persistence.ExpensePersistence;
import io.kunalvarpe.expensetracker.expense.domain.Expense;
import io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres.ExpenseEntity;
import io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres.ExpenseEntityMapper;
import io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres.ExpenseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExpensePersistenceAdapter implements ExpensePersistence {

    private final ExpenseRepository expenseRepository;
    private final ExpenseEntityMapper expenseEntityMapper;

    public ExpensePersistenceAdapter(ExpenseRepository expenseRepository, ExpenseEntityMapper expenseEntityMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseEntityMapper = expenseEntityMapper;
    }

    @Override
    public List<Expense> findExpensesByUser(String userId) {
        return expenseRepository.findByCreatedBy(userId)
                .stream()
                .map(expenseEntityMapper::fromEntity)
                .toList();
    }

    @Override
    public Expense saveExpense(Expense expense) {
        ExpenseEntity entity = expenseEntityMapper.toEntity(expense);
        return expenseEntityMapper.fromEntity(expenseRepository.save(entity));
    }

    @Override
    public Optional<Expense> getExpenseById(Integer id) {
        return expenseRepository.findById(id)
                .map(expenseEntityMapper::fromEntity);
    }

    @Override
    public void deleteById(Integer id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return expenseRepository.findById(expense.id())
                .map(expenseEntity -> {
                    expenseEntityMapper.merge(expense, expenseEntity);
                    return expenseRepository.save(expenseEntity);
                })
                .map(expenseEntityMapper::fromEntity)
                .orElseThrow();
    }
}
