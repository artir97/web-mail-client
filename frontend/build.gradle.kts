import com.github.gradle.node.npm.task.NpmTask
import org.gradle.api.tasks.Delete

plugins {
    id("com.github.node-gradle.node") version "7.1.0"
}

node {
    download = true
    version = "24.12.0"
    npmVersion = "11.6.2"
}

tasks.register<NpmTask>("build") {
    group = "build"
    dependsOn("npmSetup", "npmInstall")
    args.set(listOf("run", "build"))
    workingDir.set(project.projectDir)
}

tasks.register<NpmTask>("npmStart") {
    group = "application"
    description = "Starts the Angular frontend"

    dependsOn("npmSetup", "npmInstall")
    args.set(listOf("run", "start"))
    workingDir.set(project.projectDir)
}

tasks.register("installDist") {
    group = "distribution"
    dependsOn("build")
}

tasks.register<Delete>("clean") {
    delete(project.projectDir.resolve(".angular"))
    delete(project.projectDir.resolve(".gradle"))
    delete(project.projectDir.resolve("build"))
    delete(project.projectDir.resolve("dist"))
    delete(project.projectDir.resolve("node_modules"))
}