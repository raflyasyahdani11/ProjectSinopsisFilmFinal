package com.pcr.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pcr.navigationdrawer.R
import com.pcr.navigationdrawer.model.User
import com.pcr.navigationdrawer.utill.Constant.BASE_URL
import kotlinx.android.synthetic.main.login.*
import org.json.JSONObject

class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        loginBtn.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "$BASE_URL/user/login"

            val jsonObject = JSONObject()
            jsonObject.apply {
                put("email", txt_email.text.toString())
                put("password", txt_pass.text.toString())
            }

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                { json ->
                    val user = json.getJSONObject("data")

                    if (user.toString() != "null") {
                        Toast.makeText(this, "Berhasil masuk", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        val dataUser =
                            User(
                                id = user.getString("id_user"),
                                email = user.getString("email"),
                                name = user.getString("name")
                            )
                        intent.apply {
                            putExtra(MainActivity.EXTRA_USER, dataUser)
                        }
                        startActivity(intent)
                    }
                }, {
                    Toast.makeText(this, "${it.networkResponse.statusCode}", Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(jsonObjectRequest)
        }

        signup.setOnClickListener {
            startActivity(
                Intent(this, registerActivity::class.java)
            )
        }
    }

}