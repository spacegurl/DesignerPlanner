package com.pelepolya.designerplanner.presentation.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pelepolya.designerplanner.databinding.FragmentStartFrameBinding

class StartFrameFragment : Fragment() {

    private var _binding: FragmentStartFrameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartFrameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signinButton.setOnClickListener {
            val action = StartFrameFragmentDirections.actionStartFrameFragment2ToSignInFragment2()
            view.findNavController().navigate(action)
        }
        binding.getstarted.setOnClickListener {
            val action = StartFrameFragmentDirections.actionStartFrameFragment2ToSignUpFragment2()
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}