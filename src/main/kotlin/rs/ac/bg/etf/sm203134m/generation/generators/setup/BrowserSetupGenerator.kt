package rs.ac.bg.etf.sm203134m.generation.generators.setup

import rs.ac.bg.etf.sm203134m.semantic.TestMetadata
import java.util.*

private fun generateCaseFromBrowserName(browser: String): String {

    return """
        case("$browser"): {
            	WebDriverManager.${browser}driver().setup();
				driver = new ${browser.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}Driver();
				break;
        }            
    """
}

fun generateBrowserSetupMethod(metadata: TestMetadata): String {

    if(!metadata.requiresSelenium) {
        return "";
    }

    var code =  """
        void browserSetup(String browserName) {
            
            switch(browserName) {
            """.trimMargin()

    metadata.browserRequirements.forEach { (k, v) -> if(v) code += generateCaseFromBrowserName(k) }
    code += "}\n}"
    return code;
}