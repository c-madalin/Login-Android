package com.cst.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ProfileFragment: Fragment() {

    // Primirea argumentelor folosind navArgs()
    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Preluarea argumentului email și afișare în TextView
        val emailTextView = view.findViewById<TextView>(R.id.tv_email)

        // Afișează emailul primit
        args.email?.let {
            emailTextView.text = it
        }

        // Configurarea butonului de logout
        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            // Navigare înapoi la Navigation Auth și distrugere Navigation Profile
            val action = ProfileFragmentDirections.actionProfileFragmentToNavigationAuth()
            findNavController().navigate(action)
        }
    }
}