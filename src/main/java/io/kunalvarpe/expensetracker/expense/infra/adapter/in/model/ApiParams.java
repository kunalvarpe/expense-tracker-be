package io.kunalvarpe.expensetracker.expense.infra.adapter.in.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiParams {
    private Map<String, List<String>> filter;
}
