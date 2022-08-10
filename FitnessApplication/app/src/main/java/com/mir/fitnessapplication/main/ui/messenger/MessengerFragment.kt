package com.mir.fitnessapplication.main.ui.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R

class MessengerFragment : Fragment() {

  private lateinit var messengerViewModel: MessengerViewModel
  private lateinit var mRecyclerView: RecyclerView

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
}