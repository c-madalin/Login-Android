package com.cst.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.cst.tema1.data.User
import com.cst.tema1.data.UserRepository
import kotlinx.coroutines.launch

class RegisterStep2Fragment : Fragment() {

    // Primim argumentele din RegisterStep1Fragment
    private val args: RegisterStep2FragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_step_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            doRegister()
        }
    }

    // În RegisterStep2Fragment
    private val userRepository by lazy { UserRepository(requireContext()) }

    private fun doRegister() {
        // Preluăm emailul și parola din RegisterStep1Fragment
        val email = args.email ?: ""
        val password = args.password ?: ""

        // Preluăm numele, prenumele și vârsta din formularul curent
        val firstName = view?.findViewById<EditText>(R.id.et_first_name)?.text.toString()
        val lastName = view?.findViewById<EditText>(R.id.last_name)?.text.toString()
        val age = view?.findViewById<NumberPicker>(R.id.age)?.value

        // Salvăm utilizatorul în baza de date
        lifecycleScope.launch {
            try {
                // Verificăm dacă emailul există deja
                val emailExists = userRepository.checkEmailExists(email)

                if (emailExists) {
                    Toast.makeText(requireContext(), "Acest email există deja!", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                // Creăm utilizatorul și îl salvăm
                val user = User(
                    email = email,
                    password = password,
                    firstName = firstName,
                    lastName = lastName,
                    age = age
                )

                val userId = userRepository.registerUser(user)

                if (userId > 0) {
                    // Înregistrare reușită, navigăm către ProfileFragment
                    val navOptions = navOptions {
                        popUpTo(R.id.navigation_auth) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                    val action = RegisterStep2FragmentDirections.actionRegisterStep2FragmentToNavigationProfile(email)
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Eroare la înregistrare!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Eroare: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}