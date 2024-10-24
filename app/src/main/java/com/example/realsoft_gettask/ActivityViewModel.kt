package com.example.realsoft_gettask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realsoft_gettask.data.TasksService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val service: TasksService
) : ViewModel() {

    private val mutableState: MutableStateFlow<ActivityState> = MutableStateFlow(ActivityState())
    val state: StateFlow<ActivityState> = mutableState.asStateFlow()

    private val mutableEvents = Channel<Unit>(BUFFERED)
    val events = mutableEvents.receiveAsFlow()

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            try {
                service.getTasks().let { data ->
                    mutableState.update { it.copy(data) }
                    Log.d("GET Task Success", "Message: $data")
                }
            } catch (e: Exception) {
                Log.d("GET Tasks error", "Message: ${e.message}")
            }
        }
    }

}