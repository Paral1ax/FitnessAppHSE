package com.mir.fitnessapplication.main.ui.messenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.ui.food.dishes.DishItemStorage
import com.mir.fitnessapplication.main.ui.food.dishes.inside.InsideDishFragment
import com.mir.fitnessapplication.main.ui.messenger.friends.AddFriendFragment
import de.hdodenhof.circleimageview.CircleImageView

class MessengerRecyclerAdapter: RecyclerView.Adapter<MessengerRecyclerAdapter.ViewHolder>() {

    var dialogs = mutableListOf<UserData>()

    fun setData(exercisesList: List<UserData>) {
        this.dialogs.clear()
        this.dialogs.addAll(exercisesList)
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var image: CircleImageView? = null
        var name: TextView? = null
        var lastMessage: TextView? = null

        init {
            image = itemView?.findViewById(R.id.chat_user_profile_pic)
            name = itemView?.findViewById(R.id.companion_name_surname)
            lastMessage = itemView?.findViewById(R.id.companion_last_message)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_contact_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image?.setImageResource(R.drawable.lunch1)
        holder.name?.text = dialogs[position].username
        holder.lastMessage?.text = " "//dishes[position].text

        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val fragment = AddFriendFragment()
            itemPos = holder.absoluteAdapterPosition
            activity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return dialogs.count()
    }

    companion object {
        var itemPos: Int = 0

        fun getAdapterPos(): Int {
            return itemPos
        }
    }
}