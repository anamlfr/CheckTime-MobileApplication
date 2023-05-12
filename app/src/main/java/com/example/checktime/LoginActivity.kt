package com.example.checktime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.databinding.ActivityLoginBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAccesar.setOnClickListener{
            //startActivity(Intent(this, MenuActivity::class.java))
            validarDatos()
        }

    }

    private fun validarDatos() {
        val formBody: RequestBody = FormBody.Builder()
            .add("email", binding.txtEmail.text.toString())
            .add("password", binding.txtPass.text.toString())
            .build()

        var url = GlobalVariables.url_login
        val request = okhttp3.Request.Builder().url(url).post(formBody).build()
        val client = OkHttpClient()
        var gson = Gson()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException){
                println("error: " + e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()

                var respuestaLogin = gson.fromJson(respuesta, Modelos.RespuestaLogin::class.java)

                println("Token: " +respuestaLogin.token)
                println("Acceso: " +respuestaLogin.acceso)
                println("Error: " +respuestaLogin.error)

                if(respuestaLogin.acceso == "Ok"){

                    //Termina Base de base de datos
                    runOnUiThread{
                        var intent = Intent(this@LoginActivity, MenuActivity::class.java).apply{
                            putExtra("VAR_TOKEN",respuestaLogin.token)
                        }//var
                        //Toast.makeText(this@MainActivity, "intent: "+intent.toString(), Toast.LENGTH_LONG).show()
                        startActivity(intent)
                    }//runOn
                }//if
                else{
                    runOnUiThread{
                        Toast.makeText(this@LoginActivity, respuestaLogin.error, Toast.LENGTH_LONG).show()
                    }//runOn
                }//else
            }//OnResponse
        })
    }
}