package io.kunalvarpe.expensetracker;

import io.kunalvarpe.expensetracker.domain.expense.model.Category;
import io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres.ExpenseEntity;
import io.kunalvarpe.expensetracker.infra.expense.out.persistence.postgres.ExpenseRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseTrackerApplicationTests {

    @LocalServerPort
    private Integer port;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:14-alpine"));

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        expenseRepository.deleteAll();
    }

    @Test
    void shouldGetAllExpenses() {
        var expenseBuilder = ExpenseEntity.builder().currency("INR").userId(1);

        var expenses = List.of(
                expenseBuilder.name("diwali-shopping").category(Category.SHOPPING).amount(499.00).build(),
                expenseBuilder.name("lunch-at-ab-bbq").category(Category.FOOD).amount(1899.00).build(),
                expenseBuilder.name("Shose").category(Category.SHOPPING).amount(799.00).build()
        );

        expenseRepository.saveAll(expenses);

        var expensesList = expenseRepository.findByUserId(1);
        assertThat(expensesList)
                .isNotEmpty()
                .hasSize(3);
    }

}
