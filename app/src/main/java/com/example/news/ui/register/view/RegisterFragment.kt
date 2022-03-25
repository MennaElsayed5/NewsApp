package com.example.news.ui.register.view

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.databinding.RegisterFragmentBinding
import com.example.news.model.User
import com.example.news.model.UserRepository
import com.example.news.ui.register.view_model.RegisterViewModel
import com.example.news.ui.register.view_model.ViewModelFactory


class RegisterFragment : Fragment() {
    private lateinit var binding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory(UserRepository.getRepoInstance(requireActivity().application))
    }
    private var user: User = User("", "", "0", "")
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            if (isDataValid()) {
                user = createUser()
                viewModel.insertUser(user)
                Toast.makeText(requireContext(), "register", Toast.LENGTH_LONG).show()
                navController = Navigation.findNavController(view)
                navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())

            }
        }
    }

    private fun createUser(): User = User(
        email = binding.txtEmail.text.toString(),
        name = binding.txtName.text.toString(),
        phoneNumber = binding.txtPhone.text.toString(),
        password = binding.txtPassword.text.toString()
    )

    private fun isDataValid(): Boolean {
        val emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
        if (binding.edtEmail.editText!!.text.toString() != emailRegex) {
            binding.edtEmail.error = "Not Valid email"
        } else if (binding.edtName.editText!!.text.toString().isEmpty()) {
            binding.edtName.error = "empty_name"
        } else if (binding.edtPhoneNum.editText!!.text.toString().isEmpty()) {
            binding.edtPhoneNum.setError("empty_phone")
        } else if (binding.edtPassword.editText!!.text.toString().isEmpty()) {
            binding.edtPassword.error = "empty password"
        } else {
            return true
        }
        Toast.makeText(requireContext(), "input not valid", Toast.LENGTH_LONG).show()
        return false
    }

}