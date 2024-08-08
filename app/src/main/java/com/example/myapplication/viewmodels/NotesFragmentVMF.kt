package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesFragmentVMF(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesFragmentVM::class.java)) {
            return NotesFragmentVM(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}