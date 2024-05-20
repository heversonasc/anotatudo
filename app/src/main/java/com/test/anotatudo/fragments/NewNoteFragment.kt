package com.test.anotatudo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.test.anotatudo.MainActivity
import com.test.anotatudo.R
import com.test.anotatudo.databinding.FragmentNewNoteBinding
import com.test.anotatudo.model.Note
import com.test.anotatudo.toast
import com.test.anotatudo.viewModel.MainViewModel
import com.test.anotatudo.viewModel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewNoteBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        mView = view
    }



    private fun saveNote(view: View) {
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteBody)

            viewModel.addNote(note)
            Snackbar.make(
                view, "Nota salva com sucesso!",
                Snackbar.LENGTH_SHORT
            ).show()
            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)

        } else {
            activity?.toast("Por favor insira um Título")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_menu -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}