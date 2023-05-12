package com.example.checktime.ui.check

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is check Fragment"
    }
    val text: LiveData<String> = _text
}