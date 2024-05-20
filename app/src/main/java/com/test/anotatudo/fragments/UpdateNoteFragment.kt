package com.test.anotatudo.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.test.anotatudo.MainActivity
import com.test.anotatudo.R
import com.test.anotatudo.databinding.FragmentUpdateNoteBinding
import com.test.anotatudo.model.Note
import com.test.anotatudo.toast
import com.test.anotatudo.viewModel.MainViewModel


class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote : Note

    private lateinit var viewModel: MainViewModel

    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateNoteBinding.inflate(
            inflater, container, false
        )
       
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel


        currentNote = args.note!!
        binding.etNoteTitleUpdate.setText(currentNote.nateTitle)
        binding.etNoteBodyUpdate.setText(currentNote.nobody)

        binding.fabUpdate.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()


            if (title.isNotEmpty()){
                val note = Note(currentNote.id, title, body)
                viewModel.updateNote(note)

                activity?.toast("nota atualizada com sucesso!")

                view.findNavController().navigate(
                    R.id.action_updateNoteFragment_to_homeFragment)


            } else {
                activity?.toast("Por favor digite o título da nota")
            }
        }


    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Deletar nota")
            setMessage("Você realmente quer deletar essa nota?")
            setPositiveButton("DELETAR"){_,_->
                viewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("CANCELAR", null)
        }.create().show()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_menu -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}