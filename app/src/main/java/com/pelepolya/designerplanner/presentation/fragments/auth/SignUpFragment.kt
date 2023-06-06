package com.pelepolya.designerplanner.presentation.fragments.auth

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
import com.pelepolya.designerplanner.databinding.FragmentSignUpBinding
import com.pelepolya.designerplanner.domain.entity.SignUpUser
import com.pelepolya.designerplanner.presentation.stateholder.viewmodel.auth.SignUpViewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonSignup.setOnClickListener {
                if (validationRegistrationForm()) {
                    viewModel.signUp(
                        with(binding) {
                            SignUpUser(
                                fullName = regInputName.text.toString(),
                                phone = enterPhone.text.toString(),
                                password = enterPasswd.text.toString(),
                            )
                        }
                    )
                    val action = SignUpFragmentDirections.actionSignUpFragment2ToSignInFragment2()
                    view.findNavController().navigate(action)
                }
            }
            hintSignin.setOnClickListener {
                val action = SignUpFragmentDirections.actionSignUpFragment2ToSignInFragment2()
                view.findNavController().navigate(action)
            }
        }

    }

    private fun validationRegistrationForm(): Boolean {
        with(binding) {
            if (enterPasswd.text.toString() != enterConfirmPasswd.text.toString()) {
                textInputPassword.helperText = getString(R.string.password_confirm_error)
                enterPasswd.doOnTextChanged { _, _, _, _ ->
                    textInputPassword.helperText = ""
                }
                enterConfirmPasswd.doOnTextChanged { _, _, _, _ ->
                    textInputPassword.helperText = ""
                }
                return false
            }
            if (regInputName.text.toString().isEmpty()) {
                textInputName.helperText = getString(R.string.empty_field_error)
                regInputName.doOnTextChanged { _, _, _, _ ->
                    textInputName.helperText = ""
                }
                return false;
            }
            if (enterPhone.text.toString().length != 10) {
                textInputPhone.helperText = getString(R.string.phone_error)
                enterPhone.doOnTextChanged { _, _, _, _ ->
                    binding.textInputPhone.helperText = ""
                }
                return false;
            }
            if (enterPhone.text.toString().isEmpty()) {
                textInputPhone.helperText = getString(R.string.empty_field_error)
                enterPhone.doOnTextChanged { _, _, _, _ ->
                    textInputPhone.helperText = ""
                }
                return false;
            }
            if (enterPasswd.text.toString().isEmpty()) {
                textInputPassword.helperText = getString(R.string.empty_field_error)
                enterPasswd.doOnTextChanged { _, _, _, _ ->
                    textInputPassword.helperText = "    "
                }
                return false;
            }
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}