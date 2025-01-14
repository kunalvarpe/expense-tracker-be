package io.kunalvarpe.expensetracker.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = ArchUnitConstants.APPLICATION_BASSE_PACAKGE,
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class InterfaceRulesTest {

    @ArchTest
    private final ArchRule interfacesShouldNotHaveNameEndingWithWordInterface =
            noClasses().that().areInterfaces().should().haveNameMatching(".*Interface");

    @ArchTest
    private final ArchRule interfacesShouldNotHaveNameContainingWordInterface =
            noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface");

    @ArchTest
    private final ArchRule interfacesMustNotBePlacedInImplementationPackages =
            noClasses().that().resideInAPackage("..impl..").should().beInterfaces()
                    .allowEmptyShould(true);
}
