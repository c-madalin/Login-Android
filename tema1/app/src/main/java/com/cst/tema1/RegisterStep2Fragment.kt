package com.cst.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions

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

    private fun doRegister() {
        val navOptions = navOptions {
            popUpTo(R.id.navigation_auth) {
                inclusive = true
            }
            launchSingleTop = true
        }

        // Extragem emailul din argumente pentru a-l transmite mai departe
        val email = args.email

        val action = RegisterStep2FragmentDirections.actionRegisterStep2FragmentToNavigationProfile(email)
        findNavController().navigate(action)
    }
}