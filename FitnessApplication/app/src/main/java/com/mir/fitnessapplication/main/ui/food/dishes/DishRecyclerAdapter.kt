package com.mir.fitnessapplication.main.ui.food.dishes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.exercises.ExerciseItemStorage
import com.mir.fitnessapplication.main.ui.fitness.exercises.inside.InsideExerciseFragment
import com.mir.fitnessapplication.main.ui.food.dishes.inside.InsideDishFragment
import de.hdodenhof.circleimageview.CircleImageView

class DishRecyclerAdapter: RecyclerView.Adapter<DishRecyclerAdapter.ViewHolder>() {

    var dishes = mutableListOf<DishItemStorage>()

    fun setData(exercisesList: List<DishItemStorage>) {
        this.dishes.clear()
        this.dishes.addAll(exercisesList)
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var image: CircleImageView? = null
        var category: TextView? = null
        var text: TextView? = null

        init {
            image = itemView?.findViewById(R.id.dish_pic)
            category = itemView?.findViewById(R.id.dish_category)
            text = itemView?.findViewById(R.id.dish_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_dish_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image?.setImageResource(dishes[position].imageIdDrawable)
        holder.category?.text = dishes[position].category
        holder.text?.text = dishes[position].text

        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val fragment = InsideDishFragment()
            itemPos = holder.absoluteAdapterPosition
            activity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return dishes.count()
    }

    companion object {
        var itemPos: Int = 0

        fun getAdapterPos(): Int {
            return itemPos
        }
    }
}