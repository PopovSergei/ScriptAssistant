package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesSearchFragmentVMF(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesSearchFragmentVM::class.java)) {
            return NotesSearchFragmentVM(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}