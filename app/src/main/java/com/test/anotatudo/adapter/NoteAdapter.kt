package com.test.anotatudo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.anotatudo.databinding.FragmentNewNoteBinding
import com.test.anotatudo.databinding.ResItemNoteBinding
import com.test.anotatudo.fragments.HomeFragmentDirections
import com.test.anotatudo.model.Note
import java.util.Random

class NoteAdapter :
    RecyclerView.Adapter<NoteAdapter.MainViewHolder>()  {

    class MainViewHolder( val itemBinding: ResItemNoteBinding): RecyclerView.ViewHolder(itemBinding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ResItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.tvNoteTitle.text = currentNote.nateTitle
        holder.itemBinding.tvNoteBody.text =currentNote.nobody

        val random = Random()

            val color = Color.argb(
                255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256))
        holder.itemBinding.viewColor.setBackgroundColor(color)

        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)

            mView.findNavController().navigate (
                direction
                    )
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }


    private val differCallback =
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

        }
    val differ = AsyncListDiffer(this, differCallback)

}
