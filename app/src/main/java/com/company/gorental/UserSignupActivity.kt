package com.company.gorental

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.company.gorental.databinding.ActivityUserSignupBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class UserSignupActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserSignupBinding
    lateinit var mActivity: Activity
    //Initializing Firebase Firestore
    private var db = Firebase.firestore

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


            val Name = binding.tieSignupName.text.toString()
            val Email = binding.tieSignupEmail.text.toString()
            val contact = binding.tieSignupContact.text.toString()
            val pass = binding.tieSignupPass.text.toString()
            val Cpass = binding.tieSignupCPass.text.toString()
            if (TextUtils.isEmpty(Name)){
                binding.tieSignupName.error = "Enter Username"
                binding.tieSignupName.requestFocus()
            }
            else if (TextUtils.isEmpty(Email)){
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
            }
            else{

                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            //Firebase
            val user = hashMapOf(
                "Name" to Name,
                "Email" to Email,
                "Contact Details" to contact,
                "Password" to Cpass,
            )
            val userId = FirebaseAuth.getInstance().currentUser!!.uid

            db.collection("users").document().set(user)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully Added!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

    
}