plugins {
    kotlin("jvm") version "1.7.20"
    id("application")
    id("idea")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.kweb:kweb-core:1.1.3")
    implementation("ch.qos.logback:logback-classic:1.4.4")

    testApi(platform("io.kotest:kotest-bom:5.5.4"))

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-assertions-core")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    testImplementation("org.seleniumhq.selenium:selenium-opera-driver:4.4.0")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:4.6.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.6.0")

    testImplementation("io.github.bonigarcia:selenium-jupiter:4.3.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("kweb.template.ServerKt")
}

