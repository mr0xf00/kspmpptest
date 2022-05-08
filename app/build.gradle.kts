plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
}

group = "org.example"
version = "1.0-SNAPSHOT"

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":annotations"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

dependencies {
    add(
        "kspMetadata",
        project(":processor")
    )
}