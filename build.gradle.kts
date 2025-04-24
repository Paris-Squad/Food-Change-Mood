import groovy.xml.XmlSlurper

plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("io.insert-koin:koin-core:4.0.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.0-M1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testImplementation("io.mockk:mockk:1.13.16")
    testImplementation("com.google.truth:truth:1.4.2")
}

tasks.register("checkTestPassRate") {
    group = "Verification"

    dependsOn(tasks.test)

    doLast {
        val testResultsDir = project.file("${layout.buildDirectory.get()}/test-results/test")

        var totalTests = 0
        var failedTests = 0

        testResultsDir.walkTopDown().filter { it.name.endsWith(".xml") }.forEach { file ->
            val testsuite = XmlSlurper(false, false).parse(file)
            totalTests += testsuite.getProperty("@tests").toString().toInt()
            failedTests += testsuite.getProperty("@failures").toString().toInt()
            failedTests += testsuite.getProperty("@errors").toString().toInt()
        }

        val passedTests = totalTests - failedTests
        val passRate = if (totalTests > 0) (passedTests * 100 / totalTests) else 0

        logger.lifecycle("Total tests: $totalTests")
        logger.lifecycle("Passed tests: $passedTests")
        logger.lifecycle("Failed tests: $failedTests")
        logger.lifecycle("Pass rate: ${passRate}%")

        if (passRate < 90) throw GradleException("Test pass rate (${passRate}%) is below the required threshold (${90}%)")
    }
}

tasks.test {
    useJUnitPlatform()
    ignoreFailures = true
}