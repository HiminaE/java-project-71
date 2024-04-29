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
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation(dependencyNotation = "org.junit.jupiter:junit-jupiter")
}
//java {
//    toolchain {
//        languageVersion = JavaLanguageVersion.of(21)
//    }
//}
tasks.test {
    //useJUnitPlatform()
}