package com.cst.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText

class RegisterStep1Fragment: Fragment() {

    private val navArgs: RegisterStep1FragmentArgs by navArgs()

    private var emailEditText: EditText? = null
    private var passwordEditText: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_step_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEditText = view.findViewById(R.id.et_email)
        passwordEditText = view.findViewById(R.id.tiet_password)

        val email: String? = navArgs.email
        val password: String? = navArgs.password

        emailEditText?.setText(email)
        passwordEditText?.setText(password)

        // În RegisterStep1Fragment, modifică setOnClickListener pentru butonul Next
        view.findViewById<Button>(R.id.btn_next).setOnClickListener {
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            val action = RegisterStep1FragmentDirections.actionRegisterStep1FragmentToRegisterStep2Fragment(
                email = email,
                password = password
            )
            findNavController().navigate(action)
        }

    }
}