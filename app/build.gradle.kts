plugins {
    application
    jacoco
    id ("checkstyle")
}
application {
    mainClass = "hexlet.code.App"
}
group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.5")
    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation(dependencyNotation = "org.junit.jupiter:junit-jupiter")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0-rc1")
}
//java {
//    toolchain {
//        languageVersion = JavaLanguageVersion.of(21)
//    }
//}
tasks.test {
    //useJUnitPlatform()
}