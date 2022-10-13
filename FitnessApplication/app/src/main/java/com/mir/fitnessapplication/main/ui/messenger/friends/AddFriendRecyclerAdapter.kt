package com.mir.fitnessapplication.main.ui.messenger.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.ui.messenger.MessengerRecyclerAdapter
import de.hdodenhof.circleimageview.CircleImageView

class AddFriendRecyclerAdapter: RecyclerView.Adapter<AddFriendRecyclerAdapter.ViewHolder>() {

    private var friends = mutableListOf<ShowFriend>()

    fun setData(exercisesList: List<ShowFriend>) {
        this.friends.clear()
        this.friends.addAll(exercisesList)
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var image: CircleImageView? = null
        var name: TextView? = null
        var isCoach: SwitchCompat? = null
        var addFriendButton: Button? = null

        init {
            image = itemView?.findViewById(R.id.single_add_friend_pic)
            name = itemView?.findViewById(R.id.single_add_friend_name)
            isCoach = itemView?.findViewById(R.id.is_coach_switch)
            addFriendButton = itemView?.findViewById(R.id.add_friend_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_add_friend_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image?.setImageResource(R.drawable.lunch1)
        holder.name?.text = friends[position].name
        holder.isCoach!!.isChecked = true
        holder.addFriendButton?.setOnClickListener {
            subscribeOnUser()
        }
    }

    override fun getItemCount(): Int {
        return friends.count()
    }

    companion object {
        var itemPos: Int = 0

        fun getAdapterPos(): Int {
            return itemPos
        }
    }

    private fun subscribeOnUser() {
        //Добавление в список друзей
    }
}