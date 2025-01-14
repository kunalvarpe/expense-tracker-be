package io.kunalvarpe.expensetracker.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = ArchUnitConstants.APPLICATION_BASSE_PACAKGE,
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class NamingConventionTest {
    @ArchTest
    private final ArchRule controllersShouldBeSuffixed =
            classes()
                    .that()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .haveSimpleNameEndingWith("Controller")
                    .as("Controller should be suffixed with name 'Controller'");

    @ArchTest
    private final ArchRule controllersShouldBeAnnotatedWithRestController =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Controller")
                    .should()
                    .beAnnotatedWith(RestController.class)
                    .as("Controller should be annotated with '@RestController'");

    @ArchTest
    private final ArchRule controllerShouldBeInWebInputAdapter =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Controller")
                    .and()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .resideInAPackage(ArchUnitConstants.INPUT_ADAPTER_WEB)
                    .as("Controller should be in a package name 'adapter.in.web'");

    @ArchTest
    private final ArchRule configurationClassesShouldBeInConfigPackage =
            classes()
                    .that()
                    .areAnnotatedWith(Configuration.class)
                    .should()
                    .resideInAPackage("..config..")
                    .as("Configuration class should in package named 'config'");

}
