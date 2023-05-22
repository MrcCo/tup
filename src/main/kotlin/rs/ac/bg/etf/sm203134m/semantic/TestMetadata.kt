package rs.ac.bg.etf.sm203134m.semantic

class TestMetadata {

    var testName = ""

    // okhttp requirements
    var requiresOkhttp = false
    var requiresHeaders = false

    // assertions
    var requiresAssertions = false
    override fun toString(): String {
        return "TestMetadata(requiresOkhttp=$requiresOkhttp, requiresHeaders=$requiresHeaders, requiresAssertions=$requiresAssertions)"
    }


}