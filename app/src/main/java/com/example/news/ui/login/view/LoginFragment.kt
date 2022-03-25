package com.example.news.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentLoginBinding
import com.example.news.model.UserRepository
import com.example.news.ui.login.viewModel.LoginViewModel
import com.example.news.ui.register.view.RegisterFragmentDirections
import com.example.news.ui.register.view_model.RegisterViewModel
import com.example.news.ui.register.view_model.ViewModelFactory


class LoginFragment : Fragment() {
    lateinit var binding:FragmentLoginBinding
    lateinit var navController:NavController
    private val viewModel: LoginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory(UserRepository.getRepoInstance(requireActivity().application))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var email = (binding.txtEmail.text).toString()
        var password = (binding.txtPassword.text).toString()
        var isUserExisted = viewModel.getUser(email, password)
        if (isUserExisted) {
            binding.btnSignIn.setOnClickListener {
                navController = Navigation.findNavController(view)
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        } else {
            Toast.makeText(requireContext(), "Sorry, User not found!", Toast.LENGTH_LONG).show()
        }

    }


}