package com.example.checktime.ui.check

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_checktime.extras.CustomAdapter_Asignacion
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.R
import com.example.checktime.databinding.FragmentCheckBinding
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.time.ZonedDateTime

class CheckFragment : Fragment() {
    private var _binding: FragmentCheckBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(CheckViewModel::class.java)

        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        val root: View = binding.root

        obtenerCheck()

        setHasOptionsMenu(true)

        return root
    }

    private fun obtenerCheck() {
        val url = GlobalVariables.url_get_check
        val request = okhttp3.Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
            .get().build()
        val client = OkHttpClient()
        var gson = Gson()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error:" + e.message.toString())
                println("Error:" + e.message.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()

                activity?.runOnUiThread {
                    var objCheck = gson.fromJson(respuesta, Array<Modelos.Asignacion>::class.java)
                    binding.rvCheck.layoutManager = LinearLayoutManager(context)
                    binding.rvCheck.adapter = CustomAdapter_Asignacion(objCheck)
                }
                println("Respuesta: " + respuesta)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_check, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnNuevoCheck ->{
                var navContoller = this.view?.findNavController()
                navContoller?.navigate(R.id.nav_nuevo_check)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}