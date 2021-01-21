package com.carlostorres.iberica.ui.view.update

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
import com.carlostorres.iberica.databinding.FragmentUpdateBinding
import com.carlostorres.iberica.ui.viewmodel.NotesVM

class UpdateFragment : Fragment() {

    private val viewModel: NotesVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUpdateBinding.inflate(inflater)
        val args = UpdateFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            etTitleUpdate.setText(args.notesEntry.title)
            etDescriptionUpdate.setText(args.notesEntry.description)

            btnUpdate.setOnClickListener {
                if ( TextUtils.isEmpty(etTitleUpdate.text) && TextUtils.isEmpty(etDescriptionUpdate.text) ) {
                    Toast.makeText(requireContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val title = etTitleUpdate.text
                val desc = etDescriptionUpdate.text

                val notes = Notes(
                    args.notesEntry.id,
                    title.toString(),
                    desc.toString(),
                    args.notesEntry.timeStamp
                )

                viewModel.update(notes)
                Toast.makeText(requireContext(), getString(R.string.update_successful), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment2_to_noteFragment2)
            }

            btnDelete.setOnClickListener {
                viewModel.delete(args.notesEntry.id)

                Toast.makeText(requireContext(), getString(R.string.delete_successful), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment2_to_noteFragment2)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}