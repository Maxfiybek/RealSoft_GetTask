package com.example.realsoft_gettask

import com.example.realsoft_gettask.model.Tasks

data class ActivityState(
    val tasks: List<Tasks> = emptyList()
)
