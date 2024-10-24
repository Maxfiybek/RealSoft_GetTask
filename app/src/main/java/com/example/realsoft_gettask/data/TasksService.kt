package com.example.realsoft_gettask.data

import com.example.realsoft_gettask.model.Tasks
import retrofit2.http.GET

interface TasksService {
    @GET("api/v1/task/get_all_tasks")
    suspend fun getTasks(): List<Tasks>
}