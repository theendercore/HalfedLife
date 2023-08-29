import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.teamvoided.iridium") version "2.4.0"
    id("iridium.mod.build-script") version "2.4.0"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "guns galore"

repositories {
    mavenCentral()
}

modSettings {
    modId(base.archivesName.get())
    modName("Halfed Life")

    entrypoint("main", "com.theendercore.halfed_life.HalfedLife::commonInit")
    entrypoint("client", "com.theendercore.halfed_life.HalfedLife::clientInit")

    mixinFile("halfed_life.mixins.json")

    isModParent(true)
}

tasks {
    val targetJavaVersion = 17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
}