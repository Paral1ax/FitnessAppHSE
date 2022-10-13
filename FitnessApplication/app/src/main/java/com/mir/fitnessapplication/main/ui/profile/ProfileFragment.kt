package com.mir.fitnessapplication.main.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.entry.EntryActivity
import com.mir.fitnessapplication.main.MainActivity
import com.mir.fitnessapplication.main.ui.messenger.MessengerViewModel
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {
    private lateinit var messengerViewModel: MessengerViewModel
    private var exitButton: Button? = null
    private var profileImage: CircleImageView? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitButton = view.findViewById(R.id.button2)
        profileImage = view.findViewById(R.id.profile_image)

        exitButton?.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, EntryActivity::class.java))
        }
    }



    private fun getImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }
}