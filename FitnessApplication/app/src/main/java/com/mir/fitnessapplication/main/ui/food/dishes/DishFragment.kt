package com.mir.fitnessapplication.main.ui.food.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.AdapterFitness
import com.mir.fitnessapplication.main.ui.fitness.exercises.AllExercises
import com.mir.fitnessapplication.main.ui.food.FoodRecyclerViewAdapter

class DishFragment: Fragment() {

    private var recyclerItems: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dish_list_layout, container, false)
        //root.setOnTouchListener(OnTouchListener { v, event -> true })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        recyclerItems = view?.findViewById(R.id.dish_recyclerview)
        recyclerItems?.layoutManager = LinearLayoutManager(context)
        val adapter = DishRecyclerAdapter()
        recyclerItems?.adapter = adapter
        val list = AllDishes(FoodRecyclerViewAdapter.getAdapterPos())
        val listItem = list.getListByPosition()
        adapter.setData(listItem)
    }
}