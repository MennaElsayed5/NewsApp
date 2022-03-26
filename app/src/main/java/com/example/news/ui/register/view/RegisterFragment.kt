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
    private val viewModel: RegisterViewModel by viewModels {
        ViewModelFactory(UserRepository.getRepoInstance(requireActivity().application))
    }
    private var user: User = User("", "", "0", "")
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            it.let {
                if (!it) {
                    viewModel.insertUser(user)
                    Toast.makeText(requireContext(), "register successful", Toast.LENGTH_LONG)
                        .show()
                    navController = Navigation.findNavController(view)
                    navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())

                } else {
                    Toast.makeText(requireContext(), "User Already Existed", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        binding.btnSignUp.setOnClickListener {
            if (isDataValid()) {
                user = createUser()
                viewModel.getExistUser(user.email, user.password)

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
        val email = binding.txtEmail.text.toString()
        val regex = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!regex) {
            binding.edtEmail.error = "invalid email"
        } else if (binding.edtName.editText!!.text.toString().isEmpty()) {
            binding.edtName.error = "empty name"
        } else if (binding.edtPhoneNum.editText!!.text.toString().isEmpty()) {
            binding.edtPhoneNum.error = "empty phone"
        } else if (binding.edtPassword.editText!!.text.toString().isEmpty()) {
            binding.edtPassword.error = "empty password"
        } else {
            return true
        }
        Toast.makeText(requireContext(), "inputs invalid", Toast.LENGTH_LONG).show()
        return false
    }

}