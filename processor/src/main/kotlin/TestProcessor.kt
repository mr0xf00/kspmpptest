import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate


class TestProcessor(private val logger: KSPLogger) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation("test.ksp.annotations.Entity")
        val (valid, invalid) = symbols.partition { it.validate() }
        logger.warn(
            "found ${valid.size} valid + ${invalid.size} invalid symbols with annotation."
        )
        return invalid
    }
}


class TestProcessorProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment
    ): SymbolProcessor = TestProcessor(environment.logger)
}