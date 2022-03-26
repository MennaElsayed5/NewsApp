package com.example.news.ui.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.RegisterFragmentBinding
import com.example.news.model.UserRepository
import com.example.news.model.Users
import com.example.news.ui.register.view_model.ErrorData
import com.example.news.ui.register.view_model.RegisterRuselt
import com.example.news.ui.register.view_model.RegisterViewModel
import com.example.news.ui.register.view_model.ViewModelFactory


class RegisterFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModels {
        ViewModelFactory(UserRepository.getRepoInstance(requireActivity().application))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.registerResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                RegisterRuselt.CompleteSuccess -> {
                    Toast.makeText(requireContext(), "saved :)", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
                RegisterRuselt.DuplicateData -> {
                    Toast.makeText(requireContext(), "already exits", Toast.LENGTH_SHORT).show()
                }
                is RegisterRuselt.InvalidData -> {
                    when (result.errorData) {
                        ErrorData.EMAIL -> binding.edtEmail.error = "email invalid"
                        ErrorData.NAME -> binding.edtName.error = "name invalid"
                        ErrorData.PHONE -> binding.edtPhoneNum.error = "phone invalid"
                        ErrorData.PASSWORD -> binding.edtPassword.error = "password invalid"
                    }
                }
            }

        }
        binding.btnSignUp.setOnClickListener {
            viewModel.register(createUser())


        }
    }

    private fun createUser(): Users = Users(
        email = binding.txtEmail.text.toString(),
        name = binding.txtName.text.toString(),
        phoneNumber = binding.txtPhone.text.toString(),
        password = binding.txtPassword.text.toString()
    )


}