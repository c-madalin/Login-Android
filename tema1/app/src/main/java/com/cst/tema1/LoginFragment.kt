package com.cst.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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
            doLogin("")
        }
    }

    private fun goToRegister(email: String?, password: String?) {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterStep1Fragment(email, password)
        findNavController().navigate(action)
    }

    private fun doLogin(email: String?) {
        val action = LoginFragmentDirections.actionLoginFragmentToNavigationProfile()
        findNavController().navigate(action)
    }
}