package com.company.gorental

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.gorental.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding// view binding in gradle
    lateinit var mActivity: Activity
    private lateinit var auth: FirebaseAuth //FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Firebase initialization
        auth = Firebase.auth
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this

        val openedFor = intent.getStringExtra("CLICK");


        binding.tvNewUser.setOnClickListener {
            val LoginEmail = binding.tieLoginEmail.toString()
            val password = binding.tieLoginPass.toString()
            if (TextUtils.isEmpty(LoginEmail)) {
                binding.tieLoginEmail.error = "Enter Email"
                binding.tieLoginEmail.requestFocus()
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
            suspend fun logIn(LoginEmail: String, password: String){
                auth.signInWithEmailAndPassword(LoginEmail, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                           // updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                           // updateUI(null)
                        }
                    }

            }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //reload()
        }
    }

}