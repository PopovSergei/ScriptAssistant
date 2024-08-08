package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.NotesDao

class NoteAddShowFragmentVMF(
    private val dao: NotesDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteAddShowFragmentVM::class.java)) {
            return NoteAddShowFragmentVM(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}