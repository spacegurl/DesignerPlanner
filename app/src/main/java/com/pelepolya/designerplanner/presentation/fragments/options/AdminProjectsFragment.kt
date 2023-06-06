package com.pelepolya.designerplanner.presentation.fragments.options

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pelepolya.designerplanner.data.db.models.UserRoles
import com.pelepolya.designerplanner.databinding.FragmentAdminProjectsBinding
import com.pelepolya.designerplanner.presentation.MainActivity
import com.pelepolya.designerplanner.presentation.fragments.auth.AdminSignInFragmentDirections
import com.pelepolya.designerplanner.presentation.fragments.auth.SignInFragment
import com.pelepolya.designerplanner.presentation.stateholder.adapter.ProjectRvListAdapter
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.options.ProjectsViewModel

class AdminProjectsFragment : Fragment() {

    private var _binding: FragmentAdminProjectsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProjectsViewModel by viewModels()

    private var adapter: ProjectRvListAdapter = ProjectRvListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminProjectsBinding.inflate(inflater, container, false)
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
//                val item = adapter.projectList[viewHolder.adapterPosition]
//                viewModel.deleteProject(item.id)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        viewModel.adminProjectsListLiveData.observe(viewLifecycleOwner) {
            it.let {
                adapter.projectList = it
            }
        }
        adapter.onProjectListClickListener = { pos, title ->
            val action =
                AdminProjectsFragmentDirections.actionAdminProjectsFragmentToProjectTabLayoutFragment(
                    pos,
                    title
                )
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter

        binding.logout.setOnClickListener {
            logOut()
        }
        binding.account.setOnClickListener {
            logOut()
        }
    }

    private fun logOut() {
        val sharedPrefWrite = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPrefWrite.edit()
        editor.putString(
            SignInFragment.AUTH_SESSION,
            ""
        )
        editor.putString(
            SignInFragment.AUTH_ROLE,
            UserRoles.NONE.name
        )
        editor.apply()

        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}