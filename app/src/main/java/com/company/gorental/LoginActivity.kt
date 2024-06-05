package com.company.gorental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.company.gorental.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding// view binding in gradle
    lateinit var mActivity: Activity
    private lateinit var auth :FirebaseAuth //FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this

        val openedFor = intent.getStringExtra("CLICK");


        binding.tvNewUser.setOnClickListener {
            val username = binding.tieUsername.toString()
            val password = binding.tieLoginPass.toString()
            if (TextUtils.isEmpty(username)) {
                binding.tieUsername.error = "Enter Username"
                binding.tieUsername.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                binding.tieLoginPass.error = "Enter Password"
                binding.tieLoginPass.requestFocus()
            } else {
                if(TextUtils.equals("USER",openedFor)) {
                    val intent = Intent(this, UserSignupActivity::class.java)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, RegistrationActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

}