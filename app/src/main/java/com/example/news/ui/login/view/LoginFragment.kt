package com.example.news.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.databinding.FragmentLoginBinding
import com.example.news.model.UserRepository
import com.example.news.ui.login.viewModel.LoginViewModel
import com.example.news.ui.login.viewModel.LoginViewModelFactory


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var navController: NavController
    private val viewModel: LoginViewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(UserRepository.getRepoInstance(requireActivity().application))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            it.let {
                if (it) {
                    navController = Navigation.findNavController(view)
                    navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

                } else {
                    binding.txtEmail.error = "Email not valid"
                    binding.txtPassword.error = "Password not valid"
                }
            }
        }
        binding.btnLogIn.setOnClickListener {
            if (binding.txtEmail.text.toString().isNullOrEmpty()
                && binding.txtPassword.text.toString().isNullOrEmpty()
            ) {
                binding.txtEmail.error = "Please enter your email"
                binding.txtPassword.error = "please enter your password"
            } else {
                viewModel.getUser(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString()
                )
            }
        }
        binding.btnSignIn.setOnClickListener {
            navController = Navigation.findNavController(view)
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }


    }
}