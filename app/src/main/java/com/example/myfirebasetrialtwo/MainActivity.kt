package com.example.myfirebasetrialtwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    //allows it to connect to firebase database
     val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginbutton = findViewById<View>(R.id.mBtnLogin) as Button
        val regTxt = findViewById<View>(R.id.mTvreg) as TextView

        mBtnLogin.setOnClickListener(View.OnClickListener{
            view -> login()
        })
        mTvreg.setOnClickListener(View.OnClickListener{
                view -> register()
        })
    }

    private fun register() {
        //Allows the user to be moved from this page to the register page
        startActivity(Intent(this,Register::class.java))

    }

    private fun login() {
        val emailTxt = findViewById<View>(R.id.mTvEmail) as EditText
        val passwordTxt = findViewById<View>(R.id.mTvPassword) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        //check if user has entered email and password
        if (!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,activity_timeline::class.java))
                        Toast.makeText(this,"You have Successfully logged in",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"incorrect email or password!!",Toast.LENGTH_LONG).show()
                    }
                })

        }else{
            Toast.makeText(this,"don't leave empty fields!!",Toast.LENGTH_LONG).show()
        }
    }
}
