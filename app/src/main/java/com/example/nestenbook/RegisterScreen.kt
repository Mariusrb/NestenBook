package com.example.nestenbook

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register_screen.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.net.URL
import java.net.URLEncoder


class RegisterScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        var course = ""
        val picker = spinner
        val courses = resources.getStringArray(R.array.Courses)
        if (picker != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
            picker.adapter = adapter
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long){
                course = courses[position]
            }
        }

        registerButton.setOnClickListener {
            val name = registerName.text.toString()
            val surname = registerSurname.text.toString()
            val email = registerEmail.text.toString()
            val password = registerPassword.text.toString()
            val toast = Toast.makeText(applicationContext, "Bruker er registrert!", Toast.LENGTH_LONG).show()

            var reqParam = URLEncoder.encode("fnavn", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
            reqParam += "&" + URLEncoder.encode("enavn", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8")
            reqParam += "&" + URLEncoder.encode("epost", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
            reqParam += "&" + URLEncoder.encode("passord", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
            reqParam += "&" + URLEncoder.encode("valg", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8")

            val url = ("https://reworks.tech/gru/reg.php?android=1&$reqParam")
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            println(url)
            client.newCall(request).enqueue(object: Callback{
                override fun onResponse(call: Call, response: Response) {
                    URL(url).readText()
                    toast
                }
                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to request")
                }
            })
        }
        backToLogin.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
    }
}




