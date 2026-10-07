package com.example.gymwith.data.remote.dto

// Przechowanie strukltury danych do planów treningowych, ćwiczeń i serii w Cloud Firestore

data class WorkoutPlanDto(
    val id: String = "",
    val userId: String = "", // do filtrowania planów poprzez id użytkownika
    val title: String = "",
    val description: String? = null,
    val exercises: List<ExerciseDto> = emptyList()
)

data class ExerciseDto(
    val id: String = "",
    val name: String = "",
    val muscleGroup: String = "",
    val sets: List<ExerciseSetDto> = emptyList()
)

data class ExerciseSetDto(
    val setNumber: Int = 0,
    val reps: Int = 0,
    val weight: Double = 0.0,
    val isCompleted: Boolean = false
)