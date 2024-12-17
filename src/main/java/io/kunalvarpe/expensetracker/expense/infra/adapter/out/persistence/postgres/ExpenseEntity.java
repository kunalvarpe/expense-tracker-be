package io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres;

import io.kunalvarpe.expensetracker.expense.domain.Category;
import io.kunalvarpe.expensetracker.expense.infra.shared.AuditableEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString(callSuper = true)
@Table("expenses")
public class ExpenseEntity extends AuditableEntity<Integer> {
    private String name;
    private Category category;
    private Double amount;
    private String currency;
}
