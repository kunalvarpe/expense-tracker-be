package io.kunalvarpe.expensetracker.expense.infra.shared;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

@Data
public abstract class AuditableEntity<T> implements Persistable<T> {
    @Id
    private T id;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedBy
    private String modifiedBy;
    @LastModifiedDate
    private Instant modifiedAt;

    @Override
    public boolean isNew() {
        return modifiedAt == null;
    }
}
