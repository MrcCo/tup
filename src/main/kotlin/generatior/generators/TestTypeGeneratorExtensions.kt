package generatior.generators

import antlr4.TupParser
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach

const val SETUP_METHOD_SIGNATURE = "void setup()"
val BEFORE_EACH: String = BeforeEach::class.qualifiedName!!
val OKHTTP_CLIENT: String = OkHttpClient::class.qualifiedName!!

fun TupParser.TestTypeContext.generateOnEntry(): String {
    return """
    private ${OkHttpClient::class.simpleName} client;
    """
}

fun TupParser.TestTypeContext.generateOnExit(): String {

    return """
    @${BeforeEach::class.simpleName}
    void setup { 
        client = new ${OkHttpClient::class.simpleName}()
    }
    """
}