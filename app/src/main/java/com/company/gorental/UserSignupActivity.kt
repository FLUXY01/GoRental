package com.company.gorental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.company.gorental.databinding.ActivityUserSignupBinding
import com.github.dhaval2404.imagepicker.ImagePicker


class UserSignupActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserSignupBinding
    lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this

        binding.ivSignupCam.setOnClickListener {

             ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        binding.btSignup.setOnClickListener{


            val Username = binding.tieSignupUsername.text.toString()
            val email = binding.tieSignupEmail.text.toString()
            val contact = binding.tieSignupContact.text.toString()
            val pass = binding.tieSignupPass.text.toString()
            val Cpass = binding.tieSignupCPass.text.toString()
            if (TextUtils.isEmpty(Username)){
                binding.tieSignupUsername.error = "Enter Username"
                binding.tieSignupUsername.requestFocus()
            }
            else if (TextUtils.isEmpty(email)){
                binding.tieSignupEmail.error = "Enter Email"
                binding.tieSignupEmail.requestFocus()
            }
            else if (TextUtils.isEmpty(contact)){
                binding.tieSignupEmail.error = "Enter Contact"
                binding.tieSignupEmail.requestFocus()
            }
            else if (TextUtils.isEmpty(pass)){
                binding.tieSignupEmail.error = "Enter Password"
                binding.tieSignupEmail.requestFocus()
            }
            else if (TextUtils.isEmpty(Cpass)){
                binding.tieSignupEmail.error = "Enter Confirm Password"
                binding.tieSignupEmail.requestFocus()
            }
            else if(!TextUtils.equals(pass,Cpass)) {
                binding.tieSignupCPass.error = "Both Password Should be same"
                binding.tieSignupCPass.requestFocus()
            }else{

                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }


    }

    
}