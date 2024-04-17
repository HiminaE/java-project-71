plugins {
    application
    id ("checkstyle")
}
application { mainClass.set("hexlet.code.App")
}
group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation(dependencyNotation = "org.junit.jupiter:junit-jupiter")
}

//tasks.test {
    //useJUnitPlatform()
//}