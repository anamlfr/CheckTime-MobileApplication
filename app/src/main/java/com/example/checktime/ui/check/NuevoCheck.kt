package com.example.checktime.ui.check

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.R
import com.example.checktime.databinding.FragmentNuevoCheckBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "json_check"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevoCheck.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevoCheck : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var idCheck: Int = 0
    private var idOperadorUnidad: Int = 0

    private var _binding: FragmentNuevoCheckBinding? = null
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

    fun agregarCheck(){
        var url = GlobalVariables.url_add_check

        val formBody: RequestBody = FormBody.Builder()
            .add("id", idCheck.toString())
            .add("id_asignacion", idOperadorUnidad.toString())
            .build()

        val request = okhttp3.Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
            .post(formBody)
            .build()
        val client = OkHttpClient()
        var gson = Gson()

        client.newCall(request).enqueue(object : okhttp3.Callback {
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

    fun eliminarCheck() {
        val url = GlobalVariables.url_delete_check


        val formBody: RequestBody = FormBody.Builder()
            .add("id", idCheck.toString())
            .build()

        //println("RESPONDIO IP:" + id)
        val request = okhttp3.Request.Builder()
            .url(url)
            .header("Accept","application/json")
            .header("Authorization","Bearer " + activity?.intent?.extras?.getString("VAR_TOKEN"))
            .post(formBody)
            .build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error:" + e.message.toString())

            }


            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()
                println("RESPONDIO OK:" + respuesta)


            }
        })
    }

    fun getOperadorUnidad(){
        var url = GlobalVariables.url_get_operador


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
                var objPersonas =  objGson.fromJson(respuesta, Array<Modelos.OperadorUnidad>::class.java )


                val adapter: ArrayAdapter<Modelos.OperadorUnidad> = ArrayAdapter<Modelos.OperadorUnidad>(
                    requireActivity().baseContext,
                    android.R.layout.simple_dropdown_item_1line,
                    objPersonas
                )
                activity?.runOnUiThread {
                    binding.txtIdCheck.setAdapter(adapter)

                    binding.txtIdCheck.setOnItemClickListener { adapterView, view, i, l ->
                        idOperadorUnidad = objPersonas.get(i).id
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
        //return inflater.inflate(R.layout.fragment_nuevo_check, container, false)
        _binding = FragmentNuevoCheckBinding.inflate(inflater, container, false)
        val view = binding.root

        getOperadorUnidad()

        if(param1 != null){
            var objGson = Gson()
            var objCheck =  objGson.fromJson(param1, Modelos.Asignacion::class.java )

            idCheck = objCheck.id

            binding.txtIdCheck.setText(objCheck.nombre_personal + " " + objCheck.apaterno_personal + " " + objCheck.placas_unidad)
        }

        binding.btnRegistrar.setOnClickListener{
            agregarCheck()
        }

        binding.btnEliminar.setOnClickListener {
            eliminarCheck()
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
         * @return A new instance of fragment NuevoCheck.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevoCheck().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}