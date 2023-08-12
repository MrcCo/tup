package rs.ac.bg.etf.sm203134m.semantic

class TestMetadata {

    // api test requirements
    var requiresOkhttp = false
    var requiresObjectMapper = false

    var requiresSelenium = false
    var requiresSeleniumBy = false

    var browserRequirements = mutableMapOf(
        Pair("edge", true),
        Pair("chrome", false),
        Pair("safari", false),
        Pair("firefox", false)
    )

    var hasPreviousRequests = false

    fun formattedBrowserRequirements(): String {
        return browserRequirements.filter { it.value }.keys.joinToString { '"' + it + '"' }
    }
}