package com.example.checktime.ui.personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_checktime.extras.GlobalVariables
import com.example.app_checktime.extras.Modelos
import com.example.checktime.R
import com.example.checktime.databinding.FragmentNuevaUnidadBinding
import com.example.checktime.databinding.FragmentNuevoPersonalBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "json_personal"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevoPersonal.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevoPersonal : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var idPersonal: Int = 0

    private var _binding: FragmentNuevoPersonalBinding? = null
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
        var url = GlobalVariables.url_add_trabajador

        val formBody: RequestBody = FormBody.Builder()
            .add("id", idPersonal.toString())
            .add("cve_personal",binding.txtCvePersonal.text.toString())
            .add("nombre_personal",binding.txtNombrePersonal.text.toString())
            .add("apaterno_personal",binding.txtAparternoPersonal.text.toString())
            .add("amaterno_personal",binding.txtAmaternoPersonal.text.toString())
            .add("edad_personal",binding.txtEdadPersonal.text.toString())
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

    fun eliminarPersonal() {
        val url = GlobalVariables.url_delete_trabajador


        val formBody: RequestBody = FormBody.Builder()
            .add("id", idPersonal.toString())
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_nuevo_personal, container, false)
        _binding = FragmentNuevoPersonalBinding.inflate(inflater, container, false)
        val view = binding.root

        if(param1 != null){
            var objGson = Gson()
            var objPersonal =  objGson.fromJson(param1, Modelos.Personal::class.java )

            idPersonal = objPersonal.id

            binding.txtCvePersonal.setText(objPersonal.cve_personal)
            binding.txtNombrePersonal.setText(objPersonal.nombre_personal)
            binding.txtAparternoPersonal.setText(objPersonal.apaterno_personal)
            binding.txtAmaternoPersonal.setText(objPersonal.amaterno_personal)
            binding.txtEdadPersonal.setText(objPersonal.edad_personal)
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
         * @return A new instance of fragment NuevoPersonal.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevoPersonal().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}