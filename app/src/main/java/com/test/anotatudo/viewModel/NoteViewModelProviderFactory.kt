package com.test.anotatudo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.anotatudo.repository.NoteRepository

class NoteViewModelProviderFactory (val app: Application, private val noteRepository: NoteRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository)as T
    }
}