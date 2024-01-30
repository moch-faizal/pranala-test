package com.pranala.test.app.extensions

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun NavBackStackEntry.lifecycleIsResumed() = this.lifecycle.currentState == Lifecycle.State.RESUMED

fun NavController.navigateTo(toRoute: String, navBack: NavBackStackEntry) {
    if (navBack.lifecycleIsResumed()) {
        navigate(toRoute)
    }
}