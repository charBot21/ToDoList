package com.carlostorres.iberica.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.iberica.R
import com.carlostorres.iberica.data.local.entity.Notes
import com.carlostorres.iberica.databinding.RvNotesBinding
import com.carlostorres.iberica.model.intrfacs.HomeListener

class NotsAdapter(val clickListener: NoteClickListener ): ListAdapter<Notes, NotsAdapter.NotesViewHolder>(NotesDiffCallback) {

    companion object NotesDiffCallback: DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes) = oldItem == newItem
    }

    class NotesViewHolder( val binding: RvNotesBinding ): RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes, clickListener: NoteClickListener) {
            binding.notes = notes
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(RvNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }
}

class NoteClickListener(val clickListener: (notes: Notes) -> Unit ) {
    fun onClick(notes: Notes) = clickListener(notes)
}