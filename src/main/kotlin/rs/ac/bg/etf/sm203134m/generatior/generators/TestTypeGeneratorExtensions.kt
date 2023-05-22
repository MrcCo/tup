package rs.ac.bg.etf.sm203134m.generatior.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
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