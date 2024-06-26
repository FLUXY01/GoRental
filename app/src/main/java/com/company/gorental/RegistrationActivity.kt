package com.company.gorental

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.company.gorental.databinding.ActivityRegistrationBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding// view binding in gradle

    lateinit var mActivity: Activity

    private var db = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this
        binding.ivCam.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
//        val tieName = findViewById<TextInputEditText>(R.id.tieName)
//        val tieDob = findViewById<TextInputEditText>(R.id.tieDob)
//        val tieContactDetails = findViewById<TextInputEditText>(R.id.tieContactDetails)
//        val tieAdhaarNo = findViewById<TextInputEditText>(R.id.tieAdhaarNo)
//        val tieAddress = findViewById<TextInputEditText>(R.id.tieAddress)
//        val tieModelName = findViewById<TextInputEditText>(R.id.tieModelName)
//        val tieColour = findViewById<TextInputEditText>(R.id.tieColor)
//        val tieRegistrationNo = findViewById<TextInputEditText>(R.id.tieRegistrationNo)
//        val tieOwnerName = findViewById<TextInputEditText>(R.id.tieOwnerName)
//        val rgInsurance = findViewById<RadioGroup>(R.id.rgInsurance)
//        val btSubmit = findViewById<Button>(R.id.btSubmit)

        binding.btSubmit.setOnClickListener {
            val name = binding.tieName.text.toString()
            val insurance = if (binding.rgInsurance.checkedRadioButtonId == R.id.rgYes) "Yes" else "No"
            val dob = binding.tieDob.text.toString()
            val contactDetails = binding.tieContactDetails.text.toString()
            val email = binding.tieEmail.text.toString()
            val adhaarNo = binding.tieAdhaarNo.text.toString()
            val address = binding.tieAddress.text.toString()
            val ePass = binding.tiepass.text.toString()
            val cPass = binding.tieConfpass.text.toString()
            val ownerName = binding.tieOwnerName.text.toString()
            val modelName = binding.tieModelName.text.toString()
            val colour = binding.tieColor.text.toString()
            val registrationNo = binding.tieRegistrationNo.text.toString()
            val price = binding.tiePrice.text.toString()
            val cam = binding.ivCam.toString()
            if(TextUtils.isEmpty(name)){
                binding.tieName.error = "Enter Name"
                binding.tieName.requestFocus()
            }
            else if(TextUtils.isEmpty(email)) {
                binding.tieDob.error = "Enter Email"
                binding.tieDob.requestFocus()
            }
            else if(TextUtils.isEmpty(dob)){
                binding.tieDob.error = "Enter DOB"
                binding.tieDob.requestFocus()     
            }
            else if(contactDetails.length != 10){
                binding.tieContactDetails.error = "Enter Valid Number"
                binding.tieContactDetails.requestFocus()
            }
            else if(adhaarNo.length != 12){
                binding.tieAdhaarNo.error = "Enter Valid Adhaar Number"
                binding.tieAdhaarNo.requestFocus()
            }
            else if(TextUtils.isEmpty(address)){
                binding.tieAddress.error = "Enter Address"
                binding.tieAddress.requestFocus()
            }
            else if(TextUtils.isEmpty(ePass)){
                binding.tiepass.error = "Enter Password"
                binding.tiepass.requestFocus()
            }
            else if(TextUtils.isEmpty(cPass)){
                binding.tieConfpass.error = "Enter Confirm Password"
                binding.tieConfpass.requestFocus()
            }
            else if(!TextUtils.equals(ePass,cPass)){
                binding.tieConfpass.error = "Both Password Should be same"
                binding.tieConfpass.requestFocus()
            }
            else if(TextUtils.isEmpty(modelName)){
                binding.tieModelName.error = "Enter ModelName"
                binding.tieModelName.requestFocus()
            }
            else if(TextUtils.isEmpty(colour)){
                binding.tieColor.error = "Enter Colour"
                binding.tieColor.requestFocus()
            }
            else if(TextUtils.isEmpty(registrationNo)){
                binding.tieRegistrationNo.error = "Enter Registration No."
                binding.tieRegistrationNo.requestFocus()
            }
            else if(TextUtils.isEmpty(ownerName)){
                binding.tieOwnerName.error = "Enter Owner's Name"
                binding.tieOwnerName.requestFocus()
            }
            else if(TextUtils.isEmpty(price)){
                binding.tiePrice.error = "Enter Price"
                binding.tiePrice.requestFocus()
            }
            else if(TextUtils.isEmpty(cam)) {
                binding.tiePrice.error = "Please Upload Vehicle Photo"
                binding.tiePrice.requestFocus()
            }
            else{
                showPopUp(mActivity)
            }
            //Firebase
            val renter = hashMapOf(
                "Name" to name,
                "Date Of Birth" to dob,
                "Contact Details" to contactDetails,
                "Email" to email,
                "Adhaar No" to adhaarNo,
                "Address" to address,
                "Password" to cPass,
                "Owner Name" to ownerName,
                "Model Name" to modelName,
                "Colour" to colour,
                "Registration No" to registrationNo,
                "Price" to price,
            )
            val renterId = FirebaseAuth.getInstance().currentUser!!.uid

            db.collection("renters").document().set(renter)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully Added!",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun showPopUp(context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("User registered successfully!!")
            .setPositiveButton("Ok") { dialog, id ->
                dialog.dismiss()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
//            .setNegativeButton("Cancel") { dialog, id ->
//                // User cancelled the dialog.
//            }
        // Create the AlertDialog object and return it.
        builder.create()
        builder.show()
    }

}