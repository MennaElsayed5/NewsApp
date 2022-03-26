package com.example.news.ui.login.view

import android.os.Bundle
import android.util.Log
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
import com.example.news.ui.register.view_model.ViewModelFactory


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
        var email = binding.txtEmail.text
        var password = binding.txtPassword.text.toString()

        Log.i("variable", "onViewCreated: "+email+" "+password)

        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            it.let {
                if (it) {
                    navController = Navigation.findNavController(view)
                    navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    Log.i("true", "onViewCreated: "+email+" "+password)

                } else {
                    binding.txtEmail.error = "Email not valid"
                    binding.txtPassword.error = "Password not valid"
                    Log.i("else", "onViewCreated: "+email+" "+password)

                }
            }
        }

        binding.btnLogIn.setOnClickListener {

            Log.i("print", "onViewCreated: "+email+" "+password)
        }

        binding.btnSignIn.setOnClickListener {
            navController = Navigation.findNavController(view)
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }


    }




}