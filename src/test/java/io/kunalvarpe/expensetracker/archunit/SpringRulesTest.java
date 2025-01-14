package io.kunalvarpe.expensetracker.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.lang.conditions.ArchConditions.beAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

@AnalyzeClasses(
        packages = ArchUnitConstants.APPLICATION_BASSE_PACAKGE,
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class SpringRulesTest {

    @ArchTest
    private final ArchRule noClassesShouldUseFieldInjection =
            noFields()
                    .should(GeneralCodingRules.BE_ANNOTATED_WITH_AN_INJECTION_ANNOTATION
                            .or(beAnnotatedWith("jakarta.inject.Inject"))
                    ).as("No class should use field injection")
                    .because("Field injection is considered harmful; use constructor injection");
}
