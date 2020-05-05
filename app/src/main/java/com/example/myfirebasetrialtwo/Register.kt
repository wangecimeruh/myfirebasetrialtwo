package com.example.myfirebasetrialtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    //allows to connect to firebase database
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regbutton = findViewById<View>(R.id.mBtnRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        mBtnRegister.setOnClickListener(View.OnClickListener{
                view -> register()
        })
    }

    private fun register() {
        val emailTxt = findViewById<View>(R.id.mTvEmail) as EditText
        val passwordTxt = findViewById<View>(R.id.mTvPassword) as EditText
        val nameTxt = findViewById<View>(R.id.mTvname) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        val user = mAuth.currentUser
                        val uid =user!!.uid
                        //Allows to show name of user in session
                        mDatabase.child(uid).child("Name").setValue(name)
                        Toast.makeText(this,"Successfully signed in",Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(this,"Failed to Add user",Toast.LENGTH_LONG).show()
                    }

                })

        }else{
            Toast.makeText(this,"Please fill in all the fields",Toast.LENGTH_LONG).show()
        }
    }
}
