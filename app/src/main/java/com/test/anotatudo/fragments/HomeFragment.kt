package com.test.anotatudo.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.anotatudo.MainActivity
import com.test.anotatudo.R
import com.test.anotatudo.adapter.NoteAdapter
import com.test.anotatudo.databinding.FragmentHomeBinding
import com.test.anotatudo.model.Note
import com.test.anotatudo.viewModel.MainViewModel



class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var newbinding: FragmentHomeBinding? = null
    private val binding get() = newbinding
    private lateinit var viewModel: MainViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newbinding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

        binding?.fabAddNote?.setOnClickListener{ mView ->
            mView.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }

    }


    private fun setUpRecyclerView(){
        noteAdapter = NoteAdapter()

        binding?.recyclerView?.apply {
            layoutManager = StaggeredGridLayoutManager (
                2, StaggeredGridLayoutManager.VERTICAL
                    )
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            viewModel.getAllLives().observe(viewLifecycleOwner, {
                note -> noteAdapter.differ.submitList(note)
                updateUI(note)
            })
        }


    }

    private fun updateUI(note: List<Note>) {
            if (note.isNotEmpty()) {
                binding?.cardView?.visibility = View.GONE
                binding?.recyclerView?.visibility = View.VISIBLE

        } else {
            binding?.cardView?.visibility = View.VISIBLE
                binding?.recyclerView?.visibility = View.GONE
            }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)

        val mMenuSearch= menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = true
        mMenuSearch.setOnQueryTextListener(this)


    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchNote(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchNote(newText)
        }
        return true
    }

    private fun searchNote (query: String?){
        val searchQuery = "$query%"
        viewModel.seachNote(searchQuery).observe(this, {list ->
            noteAdapter.differ.submitList(list)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        newbinding = null
    }
}