package io.kunalvarpe.expensetracker.infra.expense.out.persistence;

import io.kunalvarpe.expensetracker.app.expense.out.persistence.ExpensePersistence;
import io.kunalvarpe.expensetracker.domain.expense.model.Expense;
import io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres.ExpenseEntity;
import io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres.ExpenseEntityMapper;
import io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres.ExpenseRepository;
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
    public List<Expense> findExpensesByUser(Integer userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(expenseEntityMapper::fromEntity)
                .toList();
    }

    @Override
    public Expense saveExpense(Expense expense) {
        ExpenseEntity entity = expenseEntityMapper.toEntity(expense);
        return expenseEntityMapper.fromEntity(entity);
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
}
