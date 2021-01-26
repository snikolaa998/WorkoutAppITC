package com.example.workoutappitc.api

import com.example.workoutappitc.api.models.Root
import com.example.workoutappitc.api.models.User
import com.example.workoutappitc.api.models.Week
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UtilsApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: String) : Call<User>

    @GET("week")
    fun getWeekEvents() : Call<Week>

    @GET("events/{id}")
    fun getSelectedEvent(@Path("id") id: String) : Call<Root>
}