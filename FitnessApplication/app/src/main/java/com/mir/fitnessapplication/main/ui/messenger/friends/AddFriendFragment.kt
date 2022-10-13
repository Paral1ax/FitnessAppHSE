package com.mir.fitnessapplication.main.ui.messenger.friends

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.ui.messenger.FriendWith
import com.mir.fitnessapplication.main.ui.messenger.MessengerRecyclerAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class AddFriendFragment: Fragment(), RecyclerViewClickListener {
    private var mRecyclerView: RecyclerView? = null
    private var searchFriends: EditText? = null
    private var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    var list: ArrayList<ShowFriend>? = null
    var refresh: SwipeRefreshLayout? = null
    var button: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_friend_layout, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFriends = view.findViewById(R.id.add_friends_by_usernames)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(FirebaseURL.DATABASE_URL)
        databaseReference = database!!.getReference("UserData")
        refresh = view.findViewById(R.id.swipe_refresh_layout)
        list = ArrayList()
        loadRecyclerView(list!!)
        searchFriends?.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (!TextUtils.isEmpty(searchFriends!!.text)) {
                    CoroutineScope(Dispatchers.IO).launch {
                        list = getUsersByNameAndSurname(searchFriends!!.text.toString())
                    }
                }
            }
        }
        refreshApp()
    }

    private fun getUsersByNameAndSurname(nameAndSurname: String): ArrayList<ShowFriend> {
        val usersList = ArrayList<ShowFriend>()
        val valueEventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val userdata = ds.getValue(UserData::class.java)
                    if (userdata!!.name.contains(nameAndSurname)) {
                        usersList.add(ShowFriend(databaseReference?.child("posts")!!.push().key!!, userdata.name, userdata.username, true))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference!!.addValueEventListener(valueEventListener)
        return usersList

    }

    private fun loadRecyclerView(list: ArrayList<ShowFriend>) {
        mRecyclerView = view?.findViewById(R.id.add_friends_recyclerview)
        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        val adapter = AddFriendRecyclerAdapter()
        mRecyclerView?.adapter = adapter
        adapter.setData(list)
    }

    private fun refreshApp() {
        refresh?.setOnRefreshListener {
            loadRecyclerView(list!!)

            refresh!!.isRefreshing = false
        }
    }
    override fun recyclerViewListClicked(v: View?, position: Int) {
        button = v?.findViewById(R.id.add_friend_button)

        button?.setOnClickListener {
            //subscribeOnUser()
        }
    }


    private fun subscribeOnUser(isChecked: Boolean, friendWith: FriendWith) {

    }

}