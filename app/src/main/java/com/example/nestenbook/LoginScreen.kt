package com.example.nestenbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            poerformLogin()
        }
        backToRegistrationText.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }

    }
    private fun poerformLogin(){
        val email = loginEmail.text.toString()
        val password = registerName.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
        } else {
            var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
            reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")

            val myURL = URL("https://itstud.hiof.no/~runeei/gru3/")

            with(myURL.openConnection() as HttpURLConnection){
            requestMethod = "POST"
                val wr = OutputStreamWriter(outputStream)
                wr.write(reqParam)
                wr.flush()

                Log.d("Con", "URL: $url")
                Log.d("Con", "Responsecode: $responseCode")



                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null){
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    Log.d("Con", "Response: $response")
                }
            }
        }

    }
}
