package com.test.anotatudo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.anotatudo.repository.NoteRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory (private val noteRepository: NoteRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this. noteRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}