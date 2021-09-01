package com.example.maintenance.Welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.maintenance.Dashboard.Maintenance
import com.example.maintenance.R
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity(), View.OnClickListener {
    private val TAG = "Welcome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btn_signin.setOnClickListener(this)
        btn_signup.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_signin) {
            val intent = Intent(this, Maintenance::class.java)
            startActivity(intent)
        } else if (view.id == R.id.btn_signup) {
            Log.d(TAG, "Tapped the sign-up button")
        }
    }
}