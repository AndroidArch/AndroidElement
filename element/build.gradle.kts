plugins {
    id("com.android.library")
    id("com.vanniktech.maven.publish") version "0.28.0"
}

android {
    namespace = "com.bigoat.element"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    compileOnly("androidx.appcompat:appcompat:1.6.1")
    compileOnly("com.google.android.material:material:1.11.0")
    compileOnly("com.github.bumptech.glide:glide:4.11.0")
//    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")
}


mavenPublishing {
    signAllPublications()
}
