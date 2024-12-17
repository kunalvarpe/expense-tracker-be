package io.kunalvarpe.expensetracker;

import io.kunalvarpe.expensetracker.expense.domain.Category;
import io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres.ExpenseEntity;
import io.kunalvarpe.expensetracker.expense.infra.adapter.out.persistence.postgres.ExpenseRepository;
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
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:alpine"));

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
        ExpenseEntity e1 = new ExpenseEntity();
        e1.setName("diwali-shopping");
        e1.setCategory(Category.SHOPPING);
        e1.setAmount(499.00);
        ExpenseEntity e2 = new ExpenseEntity();
        e2.setName("lunch-at-ab-bbq");
        e2.setCategory(Category.FOOD);
        e2.setAmount(1899.00);
        ExpenseEntity e3 = new ExpenseEntity();
        e3.setName("Shose");
        e3.setCategory(Category.SHOPPING);
        e3.setAmount(799.00);

        var expenses = List.of(e1, e2, e3);

        expenseRepository.saveAll(expenses);

        expenseRepository.findAll().forEach(System.out::println);

        var expensesList = expenseRepository.findByCreatedBy("d3dfaa27-d47e-4e5f-808f-25afdbf7016b");
        assertThat(expensesList)
                .isNotEmpty()
                .hasSize(3);
    }

}
