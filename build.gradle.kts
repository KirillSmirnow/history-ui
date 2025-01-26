plugins {
    id("java")
    id("io.freefair.lombok") version "8.7.1"
    id("org.springframework.boot") version "3.4.2"
    id("com.vaadin") version "24.4.8"
}

repositories {
    mavenCentral()
    maven(url = "https://vaadin.com/nexus/content/repositories/vaadin-addons/")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.4")
    implementation("com.vaadin:vaadin-spring-boot-starter:24.4.8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
