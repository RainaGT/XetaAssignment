package com.example.xetaassignment.dash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.xetaassignment.FoodActivity
import com.example.xetaassignment.R


class Today : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodButton: Button = view.findViewById(R.id.food)
        foodButton.setOnClickListener {
            // Start FoodActivity when the button is clicked
            val intent = Intent(activity, FoodActivity::class.java)
            startActivity(intent)
        }
    }
}

