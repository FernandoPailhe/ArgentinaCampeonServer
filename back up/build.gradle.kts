val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val koin_version: String by project


plugins {
    application
    kotlin("jvm") version "1.6.20"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
}

group = "com.ferpa"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //KMongo
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version")

    // Koin Core features
    implementation ("io.insert-koin:koin-core:$koin_version")
    // Koin Test features
    testImplementation ("io.insert-koin:koin-test:$koin_version")
    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:$koin_version")
    // SLF4J Logger
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_version")


}