package com.carlostorres.iberica.ui.view.note

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlostorres.iberica.R
import com.carlostorres.iberica.data.local.prefrns.PrefernsProvidr
import com.carlostorres.iberica.databinding.FragmentNoteBinding
import com.carlostorres.iberica.ui.adapter.NoteClickListener
import com.carlostorres.iberica.ui.adapter.NotsAdapter
import com.carlostorres.iberica.ui.view.LoginActivity
import com.carlostorres.iberica.ui.viewmodel.NotesVM

class NoteFragment : Fragment() {

    private val viewModel: NotesVM by viewModels()
    private lateinit var adapter: NotsAdapter
    private lateinit var preferencs: PrefernsProvidr

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentNoteBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        preferencs = PrefernsProvidr(requireContext())

        adapter = NotsAdapter( NoteClickListener { notes ->
            findNavController().navigate(NoteFragmentDirections.actionNoteFragment2ToUpdateFragment2(notes))
        } )

        viewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.apply {
            binding.rvItems.adapter = adapter

            fbAddNote.setOnClickListener {
                findNavController().navigate(R.id.action_noteFragment2_to_addFragment2)
            }

            fbLogout.setOnClickListener {
                preferencs.putBoolean("success", false)
                activity?.onBackPressed()
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}