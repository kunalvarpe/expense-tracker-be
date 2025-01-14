package io.kunalvarpe.expensetracker.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameEndingWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(
        packages = ArchUnitConstants.APPLICATION_BASSE_PACAKGE,
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class CodingRulesTest {

    @ArchTest
    private final ArchRule noClassesShouldAccessStandardStreams =
            NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    private final ArchRule noClassesShouldThrowGenericExceptions =
            NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule noClassesShouldUseJavaUtilLogging =
            NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    private final ArchRule mapperClassesShouldOnlyAccessDomainAndDtoObjects =
            classes()
                    .that()
                    .resideInAPackage("..adapter..")
                    .and(simpleNameEndingWith("Mapper"))
                    .should()
                    .onlyAccessClassesThat(
                            resideInAnyPackage
                                    ("..domain..", "..dto..")
                                    .or(simpleNameEndingWith("Mapper"))
                    );
}
