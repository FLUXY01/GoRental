package com.company.gorental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.company.gorental.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mActivity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this
        binding.ibRenterLogo.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("CLICK","RENTER")
            startActivity(intent)
        }
        binding.ibUserLogo.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("CLICK","USER")
            startActivity(intent)
        }
    }

}