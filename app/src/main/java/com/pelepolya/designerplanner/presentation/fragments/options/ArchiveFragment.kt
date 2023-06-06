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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pelepolya.designerplanner.databinding.FragmentArchiveBinding
import com.pelepolya.designerplanner.presentation.stateholder.adapter.ProjectRvListAdapter
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.options.ProjectsViewModel

class ArchiveFragment : Fragment() {

    private var _binding: FragmentArchiveBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProjectsViewModel by viewModels()

    private var adapter: ProjectRvListAdapter = ProjectRvListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArchiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.projectList[viewHolder.adapterPosition]
                viewModel.deleteArchive(item.id)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        viewModel.archiveListLiveData.observe(viewLifecycleOwner) {
            it.let {
                adapter.projectList = it
            }
        }
        adapter.onProjectListClickListener = { pos, title ->
            val action =
                ArchiveFragmentDirections.actionArchiveFragmentToProjectTabLayoutFragment(
                    pos,
                    title
                )
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}