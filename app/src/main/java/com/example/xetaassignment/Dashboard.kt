package com.example.xetaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.xetaassignment.dash.Camera
import com.example.xetaassignment.dash.Goals
import com.example.xetaassignment.dash.Profile
import com.example.xetaassignment.dash.Task
import com.example.xetaassignment.dash.Today
import com.example.xetaassignment.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replace(Today())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.today -> replace(Today())
                R.id.goals -> replace(Goals())
                R.id.camera -> replace(Camera())
                R.id.task -> replace(Task())
                R.id.profile -> replace(Profile())

                else->{

                }


            }
            true
        }
    }

    private fun replace(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}