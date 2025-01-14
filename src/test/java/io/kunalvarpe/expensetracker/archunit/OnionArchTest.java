package io.kunalvarpe.expensetracker.archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.properties.HasName;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.kunalvarpe.expensetracker.ExpenseTrackerApplication;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static io.kunalvarpe.expensetracker.archunit.ArchUnitConstants.INPUT_ADAPTER_WEB;
import static io.kunalvarpe.expensetracker.archunit.ArchUnitConstants.OUTPUT_ADAPTER_PERSISTENCE;

@AnalyzeClasses(
        packages = ArchUnitConstants.APPLICATION_BASSE_PACAKGE,
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class OnionArchTest {

    @ArchTest
    private final ArchRule onionArchitectureIsRespected =
            onionArchitecture()
                    .domainModels("..domain.model..")
                    .domainServices("..domain.port..")
                    .applicationServices("..domain.service..")
                    .adapter("persistence", OUTPUT_ADAPTER_PERSISTENCE)
                    .adapter("web", INPUT_ADAPTER_WEB);
//                    .ensureAllClassesAreContainedInArchitectureIgnoring("..config..", "..infra.shared..");

}
