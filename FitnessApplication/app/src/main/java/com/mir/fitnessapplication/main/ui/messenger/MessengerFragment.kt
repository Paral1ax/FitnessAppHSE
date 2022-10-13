package com.mir.fitnessapplication.main.ui.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.ui.food.FoodRecyclerViewAdapter
import com.mir.fitnessapplication.main.ui.food.dishes.AllDishes
import com.mir.fitnessapplication.main.ui.food.dishes.DishRecyclerAdapter
import com.mir.fitnessapplication.main.ui.messenger.friends.AddFriendFragment

class MessengerFragment : Fragment() {

    private lateinit var messengerViewModel: MessengerViewModel
    private var mRecyclerView: RecyclerView? = null
    private var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    private var addFriendButton: Button? = null
    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
      messengerViewModel =
              ViewModelProvider(this).get(MessengerViewModel::class.java)
      val root = inflater.inflate(R.layout.fragment_messenger, container, false)
      messengerViewModel.text.observe(viewLifecycleOwner, Observer {

      })
      return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(FirebaseURL.DATABASE_URL)
        databaseReference = database!!.getReference("FriendWith")
        addFriendButton = view.findViewById(R.id.add_new_friend_button)

        addFriendButton?.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val fragment = AddFriendFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit()
        }
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        mRecyclerView = view?.findViewById(R.id.messenger_recyclerview)
        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        val adapter = MessengerRecyclerAdapter()
        mRecyclerView?.adapter = adapter
        val list = mutableListOf(UserData("abba", "babba"))
        adapter.setData(list)
    }

    private fun getAllFriends() {

    }
}