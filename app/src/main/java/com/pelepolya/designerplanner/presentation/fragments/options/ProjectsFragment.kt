package com.pelepolya.designerplanner.presentation.fragments.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pelepolya.designerplanner.databinding.FragmentProjectsBinding
import com.pelepolya.designerplanner.presentation.stateholder.adapter.ProjectRvListAdapter
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.options.ProjectsViewModel

class ProjectsFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProjectsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.projectListLiveData.observe(viewLifecycleOwner) {
            it.let {
                val adapter = ProjectRvListAdapter(it)
                adapter.onProjectListClickListener = { pos, title ->
                    val action =
                        ProjectsFragmentDirections.actionProjectsFragment2ToProjectTabLayoutFragment(
                            pos,
                            title
                        )
                    findNavController().navigate(action)
                }
                binding.recyclerView.adapter = adapter
            }
        }
        binding.floatingActionButtonAdd.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val layout = LinearLayout(requireContext())

            layout.orientation = LinearLayout.VERTICAL

            val projectNameEt = EditText(requireContext())
            projectNameEt.hint = "Название проекта"
            layout.addView(projectNameEt)

            builder.setView(layout)
            builder.setPositiveButton("Создать") { _, _ ->
                val projectName = projectNameEt.text.toString()
                viewModel.addProject(projectName)
            }
            builder.setNegativeButton("Отменить", null)

            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}