package io.kunalvarpe.expensetracker.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

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
                    .domainModels("..domain..")
                    .applicationServices("..app..")
                    .domainServices("..app.port..")
                    .adapter("persistence", OUTPUT_ADAPTER_PERSISTENCE)
                    .adapter("web", INPUT_ADAPTER_WEB);
}
