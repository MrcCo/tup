package generatior.generators

import antlr4.TupParser
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach

fun TupParser.TestTypeContext.generateOnEntry(): String {
    return """
    private ${OkHttpClient::class.simpleName} client;
    """
}

fun TupParser.TestTypeContext.generateOnExit(): String {

    return """
    @${BeforeEach::class.simpleName}
    void setup() { 
        client = new ${OkHttpClient::class.simpleName}();
    }
    """
}