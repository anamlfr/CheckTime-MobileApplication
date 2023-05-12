package com.example.checktime.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.R
import com.example.checktime.databinding.FragmentNuevoOperadorBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "json_operador"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevoOperador.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevoOperador : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var idOperador: Int = 0
    private var idPersona: Int = 0
    private var idUnidad: Int = 0

    private var _binding: FragmentNuevoOperadorBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        println(param1)

        println("FRAGMENT TOKEN: " + activity?.intent?.extras?.getString("VAR_TOKEN"))
    }

    fun agregarPersonal(){
        var url = GlobalVariables.url_add_operador

        val formBody: RequestBody = FormBody.Builder()
            .add("id", idOperador.toString())
            .add("id_personal",idPersona.toString())
            .add("id_unidad",idUnidad.toString())
            .add("status_operador",binding.txtStatus.text.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
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
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }

    fun eliminarPersonal() {
        val url = GlobalVariables.url_delete_operador


        val formBody: RequestBody = FormBody.Builder()
            .add("id", idOperador.toString())
            .build()

        //println("RESPONDIO IP:" + id)
        val request = Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
            .post(formBody)
            .build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error:" + e.message.toString())

            }


            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println("RESPONDIO OK:" + respuesta)


            }
        })
    }


    fun getPersonal(){
        var url = GlobalVariables.url_get_trabajador


        val request = Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
            .get()
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException){
                println("error: " + e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println("Respondio Ok: " + respuesta)

                var objGson = Gson()
                var objPersonas =  objGson.fromJson(respuesta, Array<Modelos.Personal>::class.java )


                val adapter: ArrayAdapter<Modelos.Personal> = ArrayAdapter<Modelos.Personal>(
                    requireActivity().baseContext,
                    android.R.layout.simple_dropdown_item_1line,
                    objPersonas
                )
                activity?.runOnUiThread {
                    binding.txtIdPersonal.setAdapter(adapter)

                    binding.txtIdPersonal.setOnItemClickListener { adapterView, view, i, l ->
                        idPersona = objPersonas.get(i)?.id
                    }
                }

            }
        })
    }

    fun getUnidad(){
        var url = GlobalVariables.url_get_unidad


        val request = Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
            .get()
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException){
                println("error: " + e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println("Respondio Ok: " + respuesta)

                var objGson = Gson()
                var objUnidades =  objGson.fromJson(respuesta, Array<Modelos.Unidad>::class.java )


                val adapter: ArrayAdapter<Modelos.Unidad> = ArrayAdapter<Modelos.Unidad>(
                    requireActivity().baseContext,
                    android.R.layout.simple_dropdown_item_1line,
                    objUnidades
                )
                activity?.runOnUiThread {
                    binding.txtIdUnidad.setAdapter(adapter)

                    binding.txtIdUnidad.setOnItemClickListener { adapterView, view, i, l ->
                        idUnidad = objUnidades.get(i)?.id
                    }
                }

            }
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_nuevo_operador, container, false)

        _binding = FragmentNuevoOperadorBinding.inflate(inflater, container, false)
        val view = binding.root

        getPersonal()
        getUnidad()

        if(param1 != null){
            var objGson = Gson()
            var objOperador =  objGson.fromJson(param1, Modelos.Operador::class.java )

            idOperador = objOperador.id

            binding.txtIdPersonal.setText(objOperador.nombre_personal + " " + objOperador.apaterno_personal + " " + objOperador.amaterno_personal)
            binding.txtIdUnidad.setText(objOperador.cve_unidad + " " + objOperador.placas_unidad)
            binding.txtStatus.setText(objOperador.status_operador)
        }

        binding.btnRegistrar.setOnClickListener{
            agregarPersonal()
        }

        binding.btnEliminar.setOnClickListener {
            eliminarPersonal()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NuevoOperador.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevoOperador().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}