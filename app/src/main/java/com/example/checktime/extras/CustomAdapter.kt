package com.example.app_checktime.extras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.checktime.R
import com.google.gson.Gson

class CustomAdapter(private val dataSet: Array<Modelos.Registro>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView
        val txtCorreo: TextView
        val txtPassword: TextView
        val btnIniciar: Button

        init {
            // Define click listener for the ViewHolder's View.
            txtNombre = view.findViewById(R.id.txtNombre)
            txtCorreo = view.findViewById(R.id.txtEmail)
            txtPassword = view.findViewById(R.id.txtPass)
            btnIniciar= view.findViewById(R.id.btRegistrar)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_registro, viewGroup, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.btnIniciar.setOnClickListener{

            var objGson= Gson()
            var json_registro = objGson.toJson(dataSet[position])

            var navController= Navigation.findNavController(it)
            val bundle= bundleOf("json_registro" to json_registro)
            //navController.navigate(R.id.nav_nuevo_producto, bundle)

            viewHolder.txtNombre.text = dataSet[position]?.name
            viewHolder.txtCorreo.text = dataSet[position]?.email
            viewHolder.txtPassword.text = dataSet[position]?.password

        }
    }

    override fun getItemCount() = dataSet.size
}