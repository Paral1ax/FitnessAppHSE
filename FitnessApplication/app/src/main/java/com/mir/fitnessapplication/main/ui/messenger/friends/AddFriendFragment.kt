package com.mir.fitnessapplication.main.ui.messenger.friends

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.login.LoginActivity
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData


class AddFriendFragment: Fragment() {
    private var mRecyclerView: RecyclerView? = null
    private var searchFriends: EditText? = null
    private var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var list: ArrayList<ShowFriend>? = null
    var refresh: SwipeRefreshLayout? = null

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
        refresh = view.findViewById(R.id.swipe_refresh_layout)
        list = ArrayList()
        loadRecyclerView(list!!)
        searchFriends?.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (!TextUtils.isEmpty(searchFriends!!.text)) {
                    Thread {
                        list = getUsersByNameAndSurname(searchFriends!!.text.toString())
                        val activity = context as AppCompatActivity
                        activity.runOnUiThread {
                            loadRecyclerView(list!!)
                        }
                    }.start()
                }
            }
        }
        refreshApp()
    }

    private fun getUsersByNameAndSurname(nameAndSurname: String): ArrayList<ShowFriend> {
        val usersList = ArrayList<ShowFriend>()
        val reference = database?.getReference("UserData")
        val valueEventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val userdata = ds.getValue(UserData::class.java)
                    if (userdata!!.name.contains(nameAndSurname) && ds.key != auth?.uid) {
                        usersList.add(ShowFriend(ds.key!!, userdata.name, userdata.username, true))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        reference!!.addValueEventListener(valueEventListener)
        return usersList

    }

    private fun loadRecyclerView(list: ArrayList<ShowFriend>) {
        mRecyclerView = view?.findViewById(R.id.add_friends_recyclerview)
        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        val adapter = AddFriendRecyclerAdapter()
        mRecyclerView?.adapter = adapter
        adapter.attachDelegate(object : RecyclerViewClickListener{
            override fun recyclerViewListClicked(position: Int) {
                subscribeOnUser(list[position])
            }
        })
        adapter.setData(list)
    }

    private fun refreshApp() {
        refresh?.setOnRefreshListener {
            loadRecyclerView(list!!)

            refresh!!.isRefreshing = false
        }
    }


    private fun subscribeOnUser(subscribeOn: ShowFriend) {
        val reference = database?.getReference("Subscribed")
        reference?.child(FirebaseAuth.getInstance().currentUser!!.uid)!!.setValue(subscribeOn)
            .addOnCompleteListener {
                Toast.makeText(this.context, "Successful Subscribed", Toast.LENGTH_SHORT).show()
            }
    }

}