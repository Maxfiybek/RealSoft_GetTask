package com.example.realsoft_gettask.model


import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Tasks(
    @SerialName("executor_avatar")
    val executorAvatar: String,

    @SerialName("executor_id")
    val executorId: Int,

    @SerialName("executor_name")
    val executorName: String,

    @SerialName("index")
    val index: Int,

    @SerialName("name")
    val name: String,

    @SerialName("owner_avatar")
    val ownerAvatar: String,

    @SerialName("owner_id")
    val ownerId: Int,

    @SerialName("owner_name")
    val ownerName: String,

    @SerialName("priority")
    val priority: String,

    @SerialName("project_id")
    val projectId: String,

    @SerialName("project_name")
    val projectName: String,

    @SerialName("status")
    val status: String,

    @SerialName("task_date")
    val taskDate: String,

    @SerialName("task_id")
    val taskId: Int,

    @SerialName("term_date")
    val termDate: String
)