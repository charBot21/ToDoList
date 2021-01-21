package com.carlostorres.iberica.ui.view.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlostorres.iberica.R
import com.carlostorres.iberica.data.local.entity.Notes
import com.carlostorres.iberica.databinding.FragmentAddBinding
import com.carlostorres.iberica.ui.viewmodel.NotesVM

class AddFragment : Fragment() {

    private val viewModel: NotesVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAddBinding.inflate(inflater)

        binding.apply {
            btnSave.setOnClickListener {
                if ( TextUtils.isEmpty(etTitle.text) && TextUtils.isEmpty(etDescription.text) ) {
                    Toast.makeText(requireContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val title = etTitle.text.toString()
                val description = etDescription.text.toString()

                val addNote = Notes(
                    0,
                    title,
                    description,
                        System.currentTimeMillis()
                )

                viewModel.insert(addNote)
                Toast.makeText(requireContext(), getString(R.string.add_successful), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment2_to_noteFragment2)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}