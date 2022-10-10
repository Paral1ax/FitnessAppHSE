package com.mir.fitnessapplication.main.ui.fitness.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.AdapterFitness


class ExerciseFragment: Fragment() {

    private var recyclerItems: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.exercises_list_layout, container, false)
        root.setOnTouchListener(OnTouchListener { v, event -> true })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        recyclerItems = view?.findViewById(R.id.exercise_recyclerview)
        recyclerItems?.layoutManager = LinearLayoutManager(context)
        val adapter = ExerciseRecyclerAdapter()
        recyclerItems?.adapter = adapter
        val list = AllExercises(AdapterFitness.getAdapterPos())
        val listItem = list.getListByPosition()
        adapter.setData(listItem)
    }
}