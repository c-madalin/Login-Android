package com.cst.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.cst.tema1.data.UserRepository
import kotlinx.coroutines.launch

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_go_to_register).setOnClickListener {
            val email: String = view.findViewById<EditText>(R.id.et_email).text.toString()
            val password: String = view.findViewById<EditText>(R.id.tiet_password).text.toString()

            goToRegister(email, password)
        }

        view.findViewById<Button>(R.id.btn_do_login).setOnClickListener {
            doLogin()
        }
    }

    private fun goToRegister(email: String?, password: String?) {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterStep1Fragment(email, password)
        findNavController().navigate(action)
    }

    // În LoginFragment
    private val userRepository by lazy { UserRepository(requireContext()) }

    private fun doLogin() {
        val email = view?.findViewById<EditText>(R.id.et_email)?.text.toString()
        val password = view?.findViewById<EditText>(R.id.tiet_password)?.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "Introduceți emailul și parola", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val user = userRepository.loginUser(email, password)

                if (user != null) {
                    // Autentificare reușită
                    val navOptions = navOptions {
                        popUpTo(R.id.navigation_auth) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                    val action = LoginFragmentDirections.actionLoginFragmentToNavigationProfile(email)
                    findNavController().navigate(action)
                } else {
                    // Autentificare eșuată
                    Toast.makeText(requireContext(), "Email sau parolă incorecte", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Eroare: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}