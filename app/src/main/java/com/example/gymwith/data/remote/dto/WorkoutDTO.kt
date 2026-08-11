package com.example.gymwith.data.remote.dto

// Przechowanie strukltury danych do planów treningowych, ćwiczeń i serii w formacie gotowym do przesłania przez JSON

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutPlanDto(
    val id: String? = null,
    val title: String,
    val description: String?,
    val exercises: List<ExerciseDto>
)

@Serializable
data class ExerciseDto(
    val id: String? = null,
    val name: String,
    val muscleGroup: String,
    val sets: List<ExerciseSetDto>
)

@Serializable
data class ExerciseSetDto(
    val setNumber: Int,
    val reps: Int,
    val weight: Double,
    val isCompleted: Boolean = false
)