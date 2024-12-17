package io.kunalvarpe.expensetracker.expense.infra.shared;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface LocalDateTimeMapper {
    default Instant toInstant(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(ldt -> ldt.toInstant(ZoneOffset.UTC))
                .orElse(null);
    }

    default LocalDateTime fromInstant(Instant instant) {
        return Optional.ofNullable(instant)
                .map(inst -> LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                .orElse(null);
    }
}
