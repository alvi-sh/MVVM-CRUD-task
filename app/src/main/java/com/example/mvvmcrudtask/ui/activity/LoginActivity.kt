package com.example.mvvmcrudtask.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvvmcrudtask.R
import com.example.mvvmcrudtask.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    var auth = FirebaseAuth.getInstance()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE)
//        if (isUserLoggedIn()) {
//            navigateToDashboard()
//        }

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.loginButton.setOnClickListener {
            var email = loginBinding.loginEmail.text.toString()
            var password = loginBinding.loginPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
//                    saveLoginStatus()
//                    navigateToDashboard()
                } else {
                    Toast.makeText(applicationContext, task.exception?.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        loginBinding.gotoSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

//    private fun isUserLoggedIn(): Boolean {
//        return sharedPreferences.getBoolean("isLoggedIn", false)
//    }
//
//    private fun saveLoginStatus() {
//        val editor = sharedPreferences.edit()
//        editor.putBoolean("isLoggedIn", true)
//        editor.apply()
//    }
//
//    private fun navigateToDashboard() {
//        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}
