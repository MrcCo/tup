package rs.ac.bg.etf.sm203134m.generation.generators

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CommonsTest {

    @Test
    fun importStatementTest() {

        // given
        val className = Test::class.qualifiedName!!

        // when
        val generatedImport = Commons.importStatement(className)

        // then
        Assertions.assertEquals("import org.junit.jupiter.api.Test;\n", generatedImport)

    }
}