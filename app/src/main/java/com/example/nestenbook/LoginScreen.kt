package com.example.nestenbook

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.internal.wait
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            performLogin()
        }
        backToRegistrationText.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }

    }

    private fun performLogin() {
        val email = loginEmail.text.toString()
        val password = registerName.text.toString()
        val intent = Intent(this, MessageScreen::class.java)

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
        } else {
            var reqParam =
                URLEncoder.encode("brukernavn", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
            reqParam += "&" + URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(password,"UTF-8")
            val myURL = URL("https://reworks.tech/gru/android/login.php?android=1&$reqParam")

            val request = Request.Builder().url(myURL).build()
            val client = OkHttpClient()
            println(myURL)
            client.newCall(request).enqueue(object: Callback{
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    println(body)
                    if (body != null) {
                        if(body.contains("true")){

                            var userid: String = body[5].toString()
                            userid += body[6].toString()
                            userid += body[7].toString()
                            userid += body[8].toString()
                            userid += body[9].toString()
                            userid += body[10].toString()
                            userid += body[11].toString()

                            println(userid)
                            intent.putExtra("USER_ID", userid)
                            startActivity(intent)
                        }
                        if (body.contains("false")){
                            //do logging
                        }
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to request")
                }
            })
        }
    }
}


