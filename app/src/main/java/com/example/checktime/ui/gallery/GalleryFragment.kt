package com.example.checktime.ui.gallery

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_checktime.extras.CustomAdapter_Operador
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.R
import com.example.checktime.databinding.FragmentGalleryBinding
import com.example.checktime.extras.CustomAdapter_Personal
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        obtenerOperador()

        setHasOptionsMenu(true)

        return root
    }

    private fun obtenerOperador() {
        val url = GlobalVariables.url_get_operador
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
                    var objOperador = gson.fromJson(respuesta, Array<Modelos.Operador>::class.java)
                    binding.rvOperador.layoutManager = LinearLayoutManager(context)
                    binding.rvOperador.adapter = CustomAdapter_Operador(objOperador)
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
        inflater.inflate(R.menu.menu_operador, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnNuevoOperador ->{
                var navContoller = this.view?.findNavController()
                navContoller?.navigate(R.id.nav_nuevo_operador)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}