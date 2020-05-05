package com.example.myfirebasetrialtwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class activity_timeline : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val displaytxt = findViewById<View>(R.id.mTvDisplay) as TextView
         mDatabase = FirebaseDatabase.getInstance().getReference("Names")
      mDatabase.addValueEventListener(object : ValueEventListener{
          override fun onCancelled(p0: DatabaseError) {

          }

          override fun onDataChange(snapshot: DataSnapshot) {
        val result = snapshot.child("Name").toString()
              displaytxt.text = "Welcome"+result
          }
      })
    }

   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!!.itemId == R.id.signout) {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "logged out", Toast.LENGTH_LONG).show()
        }
       return super.onOptionsItemSelected(item)
    }
}
