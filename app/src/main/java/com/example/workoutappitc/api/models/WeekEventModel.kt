package com.example.workoutappitc.api.models

import com.google.gson.annotations.SerializedName

data class Equipment(val item: String)

data class Workout(val title: String, val background: String, val time: Int, val equipment: List<Equipment>)

data class Recipe(@SerializedName("title:") val title: String, val background: String)

data class Mindset(@SerializedName("title:") val title: String, val background: String)

data class Root(val workout: Workout, val recipe: Recipe, val mindset: Mindset, val workout_tip: String, val completed: Boolean)