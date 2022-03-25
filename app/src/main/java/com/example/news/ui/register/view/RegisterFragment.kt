package com.example.news.ui.register.view

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.news.databinding.RegisterFragmentBinding
import com.example.news.model.User
import com.example.news.model.UserRepository
import com.example.news.ui.register.view_model.RegisterViewModel
import com.example.news.ui.register.view_model.ViewModelFactory
import java.util.regex.Pattern


class RegisterFragment : Fragment() {
    private lateinit var binding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory(UserRepository.getRepoInstance(requireActivity().application))
    }
    private var user: User = User("", "", "0", "")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = User("menna@gmail.com", "menna", "2222", "12346")
        viewModel.insertUser(user)
    }

    private fun createUser(): User =
        User(
            email = checkEmail(binding.txtEmail.text.toString()).toString(),
            name = binding.txtName.text.toString(),
            phoneNumber = binding.txtPhone.text.toString(),
            password = binding.txtPassword.text.toString()
        )

    private fun checkEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}