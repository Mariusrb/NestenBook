package com.example.nestenbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_message_screen.*
import okhttp3.*
import java.io.IOException
import java.net.URL


class MessageScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_screen)

        val userid = intent.getStringExtra("USER_ID")

        onVerifiedLoggin()

        sendMessage.setOnClickListener {
            println(userid)
        }
    }


    private fun onVerifiedLoggin(){
        val myURL = URL("https://reworks.tech/gru/android/melding.php?android=1&bid=test%40test.test&pwd=test&fag=ITD15019&msg=12345")
        val userid = intent.getStringExtra("USER_ID")
        val request = Request.Builder().url(myURL).build()
        val client = OkHttpClient()
        println(myURL)
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                runOnUiThread {
                    displayMessage.text = body }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to request")
            }
        })
    }
}
