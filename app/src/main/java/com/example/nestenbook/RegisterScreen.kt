package com.example.nestenbook

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register_screen.*


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
            Log.d("Test", "$name $surname $email $password $course")
        }
    }
}



