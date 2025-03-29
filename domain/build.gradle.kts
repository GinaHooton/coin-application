plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.hilt.android)
    testImplementation(libs.junit)
}