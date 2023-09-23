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

val modid = base.archivesName.get()


repositories {
    mavenCentral()
    maven("brokenfuse") {
        url = uri("https://maven.teamvoided.org/releases")
    }

}

modSettings {
    modId(modid)
    modName("Halfed Life")

    entrypoint("main", "com.theendercore.halfed_life.HalfedLife::commonInit")
    entrypoint("client", "com.theendercore.halfed_life.HalfedLife::clientInit")
    entrypoint("fabric-datagen", "com.theendercore.halfed_life.HalfedLifeData")

    mixinFile("halfed_life.mixins.json")

    isModParent(true)
}

dependencies {
    modImplementation("org.teamvoided:voidlib-core:1.5.3+1.20.1")
}

loom {
    runs {
        create("data") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${modid}")
            runDir("build/datagen")
        }
    }
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

sourceSets["main"].resources.srcDir("src/main/generated")