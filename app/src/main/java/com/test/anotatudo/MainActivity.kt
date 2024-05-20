package com.test.anotatudo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.anotatudo.databinding.ActivityMainBinding
import com.test.anotatudo.db.NoteDatabase
import com.test.anotatudo.repository.NoteRepository
import com.test.anotatudo.viewModel.MainViewModel
import com.test.anotatudo.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }


    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = MainViewModelFactory(noteRepository)

       viewModel= ViewModelProvider(this, viewModelProviderFactory)
          .get(MainViewModel::class.java)
    }
}
