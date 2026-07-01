import org.gradle.kotlin.dsl.base

plugins {
    base
}

tasks.register("dev") {
    group = "application"
    description = "Starts backend and frontend"

    dependsOn(":backend:bootRun")
    dependsOn(":frontend:npmStart")
}