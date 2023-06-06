package com.pelepolya.designerplanner.presentation.fragments.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.databinding.FragmentProjectNotesBinding
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.options.ProjectsViewModel
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.project.ProjectNotesViewModel
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.project.ProjectNotesViewModelFactory

class ProjectNotesFragment : Fragment() {

    private var _binding: FragmentProjectNotesBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy {
        ProjectNotesViewModelFactory(
            requireActivity().application,
            projectPosition,
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProjectNotesViewModel::class.java]
    }

    private var projectPosition = -1
    private lateinit var note: ProjectNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.NoteContent.setStylesBar(binding.BottomBar)

        viewModel.projectListLiveData.observe(viewLifecycleOwner) {
            if (projectPosition > 0) {
                note = it[projectPosition]
                if (::note.isInitialized) {
                    viewModel.loadProjectNote()
                }
            }
        }

        viewModel.bodyNoteLiveData.observe(viewLifecycleOwner) {
            binding.NoteContent.setText(it.body)
        }
        binding.NoteContent.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (::note.isInitialized) {
                    viewModel.saveProjectNote(note.id, binding.NoteContent.text.toString())
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (::note.isInitialized) {
            viewModel.saveProjectNote(
                note.id, binding.NoteContent.text.toString()
            )
        }
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (args.containsKey(PROJECT_POSITION)) {
            projectPosition = args.getInt(PROJECT_POSITION)
        }
    }

    companion object {

        private const val PROJECT_POSITION = "projectPosition"

        fun newInstance(projectPosition: Int): ProjectNotesFragment {
            return ProjectNotesFragment().apply {
                arguments = Bundle().apply {
                    putInt(PROJECT_POSITION, projectPosition)
                }
            }
        }
    }
}