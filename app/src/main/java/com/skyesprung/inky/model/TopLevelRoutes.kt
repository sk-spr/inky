package com.skyesprung.inky.model

import kotlinx.serialization.Serializable

@Serializable
sealed class TopLevelRoutes {
    @Serializable
    data object AppScreen : TopLevelRoutes();
    @Serializable
    data object NoteScreen : TopLevelRoutes();
    @Serializable
    data object BookScreen : TopLevelRoutes();
}