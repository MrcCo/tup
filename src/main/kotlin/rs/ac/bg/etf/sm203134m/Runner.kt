package rs.ac.bg.etf.sm203134m

fun main() {
    val case = TestCase("src/main/resources/selenium.tup")
    case.writeCode("src/test/kotlin")
    println(case.isSemanticallyValid())
    println(case.getSemanticErrorsAndWarnings())
}