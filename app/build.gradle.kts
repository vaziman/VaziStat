import com.google.wireless.android.sdk.stats.GradleBuildProject.PluginType

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")


}

//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath ("com.android.tools.build:gradle:4.2.2")
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.2")
//    }
//}


android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.10")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation ("androidx.core:core-ktx:1.13.1")

    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.privacysandbox.tools:tools-core:1.0.0-alpha09")
    implementation("androidx.compose.animation:animation-graphics-android:1.6.8")
    testImplementation("junit:junit:4.13.2")
    implementation("com.google.code.gson:gson:2.10")

    // room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // corutines
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")

    //Test JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    //Mockito
    testImplementation ("com.chairman.PowerMockito2:mockito-kotlin:2.1.0")
    testRuntimeOnly ("org.mockito:mockito-core:5.7.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.3.1")

    implementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation ("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

