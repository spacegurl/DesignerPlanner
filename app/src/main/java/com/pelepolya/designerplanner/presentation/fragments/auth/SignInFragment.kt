package com.pelepolya.designerplanner.presentation.fragments.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.databinding.FragmentSignInBinding
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.presentation.viewmodel.auth.SignInViewModel

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonSignin.setOnClickListener {
                viewModel.validAuth(
                    SignInUser(
                        phone = enterPhone.text.toString(),
                        password = enterPasswd.text.toString()
                    )
                )
                viewModel.checkAuthValid.observe(viewLifecycleOwner) {
                    if (it) {
                        val sharedPrefWrite = requireActivity().getPreferences(Context.MODE_PRIVATE)
                        val editor = sharedPrefWrite.edit()
                        editor.putString(
                            AUTH_SESSION,
                            enterPhone.text.toString()
                        )
                        editor.apply()

                        val action = SignInFragmentDirections.actionSignInFragment2ToMenuFragment()
                        view.findNavController().navigate(action)
                    } else {
                        textInputPhone.helperText = getString(R.string.invalid_error)
                        enterPhone.doOnTextChanged { _, _, _, _ ->
                            textInputPhone.helperText = ""
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val AUTH_SESSION = "auth"
    }

}