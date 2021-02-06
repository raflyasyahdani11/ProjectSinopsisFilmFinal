package com.pcr.navigationdrawer.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pcr.navigationdrawer.R
import com.pcr.navigationdrawer.utill.Constant
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class registerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        register_btn.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "${Constant.BASE_URL}/user/register"

            val jsonObject = JSONObject()
            jsonObject.apply {
                put("name", txt_name.text.toString())
                put("email", txt_email.text.toString())
                put("password", txt_pass.text.toString())
            }

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                {
                    Toast.makeText(this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()
                    finish()
                }, {
                    Toast.makeText(this, it.cause.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            )

            queue.add(jsonObjectRequest)
        }

        signin.setOnClickListener {
            startActivity(
                Intent(this, loginActivity::class.java)
            )
        }
    }

}