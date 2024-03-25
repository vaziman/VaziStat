package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.myapplication.R

class ListMoreFragment : Fragment() {
    lateinit var listView: ListView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.findViewById<ListView>(R.id.list_view_menu)

        var menuList = arrayListOf(
            "Activities",
            "Health Stats",
            "Performance Stats",
            "Gear",
            "Devices",
            "Settings",
            "Help",
            "Activity Tracking Accuracy"
        )


        var adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, menuList)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        listView.setOnItemClickListener { parent, view, position, id ->
            if(position == 0){
                Toast.makeText(requireContext(), "Position: $position", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_list_more, container, false)
        listView = rootView.findViewById(R.id.list_view_menu)  // initialization listView
        return rootView


    }

    companion object {
        @JvmStatic
        fun newInstance() = ListMoreFragment()
    }


}