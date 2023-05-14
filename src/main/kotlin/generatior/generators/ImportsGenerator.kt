package generatior.generators

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import semantic.TestMetadata
import java.io.IOException

fun generateImportsFromMetadata(metadata: TestMetadata): String {
    var imports = """
        import ${Test::class.qualifiedName!!};
        import ${BeforeEach::class.qualifiedName!!};
        """.trimIndent()

    if(metadata.requiresAssertions) {
        imports += "\nimport ${Assertions::class.qualifiedName};\n";
    }

    imports += "\n"

    if(metadata.requiresOkhttp) {
        imports += """
            import ${OkHttpClient::class.qualifiedName!!};
            import ${Request::class.qualifiedName!!};
            import ${Response::class.qualifiedName!!}
            
            import ${IOException::class.qualifiedName!!}
        """.trimIndent()
        imports += "\n\n"
    }

    return imports;
}