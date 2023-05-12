package com.example.checktime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.databinding.ActivityLoginBinding
import com.example.checktime.databinding.ActivityRegistroBinding
import com.example.checktime.databinding.FragmentNuevoCheckBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private var idUser: Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_registro)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegistrar.setOnClickListener{
            //startActivity(Intent(this, MenuActivity::class.java))
            agregarPersonal()
        }
    }

    fun agregarPersonal(){
        var url = GlobalVariables.url_registro

        val formBody: RequestBody = FormBody.Builder()
            .add("id", idUser.toString())
            .add("name",binding.txtNombre.text.toString())
            .add("email",binding.txtEmail.text.toString())
            .add("password",binding.txtPass.text.toString())
            .add("perfil",binding.txtPerfil.text.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + intent?.extras?.getString("VAR_TOKEN"))
            .post(formBody)
            .build()
        val client = OkHttpClient()
        var gson = Gson()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException){
                println("error: " + e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println("Respondio Ok: " + respuesta)
                runOnUiThread{
                    var intent = Intent(this@RegistroActivity, LoginActivity::class.java).apply{
                        putExtra("VAR_TOKEN","token")
                    }//var
                    //Toast.makeText(this@MainActivity, "intent: "+intent.toString(), Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }//runOn
            }
        })
    }
}