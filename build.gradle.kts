plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "io.turbo.random"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven {
        url = uri("https://repo.panda-lang.org/releases")
    }
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.10-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    implementation("dev.rollczi:litecommands-bukkit:3.10.2")
    implementation("fr.mrmicky:fastinv:3.1.2")
}

tasks {
    runServer {
        minecraftVersion("1.21")
    }
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    relocate("fr.mrmicky.fastinv", "io.turbo.random.quackcmd.libs.fastinv")
    relocate("dev.rollczi.litecommands", "io.turbo.random.quackcmd.libs.litecommands")
}

tasks.compileJava {
    options.compilerArgs.add("-parameters")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
