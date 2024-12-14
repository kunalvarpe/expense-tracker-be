package io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres;

import java.time.LocalDateTime;

import io.kunalvarpe.expensetracker.domain.expense.model.Category;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("expenses")
@Data
@Builder
public class ExpenseEntity {
    @Id
    private Integer id;
    private String name;
    private Category category;
    private Double amount;
    private String currency;
    private Integer userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
