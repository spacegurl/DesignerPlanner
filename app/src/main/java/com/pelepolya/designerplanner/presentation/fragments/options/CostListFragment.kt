package com.pelepolya.designerplanner.presentation.fragments.options

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.data.db.models.UserRoles
import com.pelepolya.designerplanner.databinding.FragmentCostListBinding
import com.pelepolya.designerplanner.presentation.MainActivity
import com.pelepolya.designerplanner.presentation.fragments.auth.SignInFragment

class CostListFragment : Fragment() {

    private var _binding: FragmentCostListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.account.setOnClickListener {
            logOut()
        }
        binding.logout.setOnClickListener {
            logOut()
        }

        binding.patternButton.setOnClickListener {
//            val action = CostListFragmentDirections.actionCostListFragmentToCostListTabLayoutFragment()
            findNavController().navigate(R.id.action_costListFragment_to_costListTabLayoutFragment2)
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