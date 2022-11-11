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
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("kweb.template.ServerKt")
}

