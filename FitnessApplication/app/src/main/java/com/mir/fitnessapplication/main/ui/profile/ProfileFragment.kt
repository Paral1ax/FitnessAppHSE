package com.mir.fitnessapplication.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.messenger.MessengerViewModel

class ProfileFragment : Fragment() {
    private lateinit var messengerViewModel: MessengerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messengerViewModel =
            ViewModelProvider(this).get(MessengerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        messengerViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}