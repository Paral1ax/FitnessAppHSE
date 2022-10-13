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
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.entry.EntryActivity
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.MainActivity
import com.mir.fitnessapplication.main.ui.messenger.MessengerViewModel
import com.mir.fitnessapplication.main.ui.messenger.friends.ShowFriend
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {
    private lateinit var messengerViewModel: MessengerViewModel
    private var exitButton: Button? = null
    private var profileImage: CircleImageView? = null
    private var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

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
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(FirebaseURL.DATABASE_URL)
        databaseReference = database!!.getReference("UserData")
        exitButton?.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, EntryActivity::class.java))
        }
    }

    private fun getProfileData(): UserData? {
        var userData: UserData? = null
        val valueEventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val userdata = ds.getValue(UserData::class.java)
                    if (databaseReference?.child("posts")!!.push().key!! == auth?.uid) {
                        userData = UserData(userdata!!.name, userdata!!.username)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference!!.addValueEventListener(valueEventListener)
        return userData
    }
}