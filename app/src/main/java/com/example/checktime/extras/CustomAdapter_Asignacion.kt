package com.example.app_checktime.extras

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.checktime.R
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CustomAdapter_Asignacion(private val dataSet: Array<Modelos.Asignacion>) :
    RecyclerView.Adapter<CustomAdapter_Asignacion.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPlacas: TextView
        val txtNombre: TextView
        val txtHora: TextView


        init {
            // Define click listener for the ViewHolder's View.
            txtPlacas = view.findViewById(R.id.txtPlacas_unidad)
            txtNombre = view.findViewById(R.id.txtNombre_personal)
            txtHora = view.findViewById(R.id.txtHora)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_check, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
   // @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemView.setOnClickListener{

            var objGson= Gson()
            var json_check = objGson.toJson(dataSet[position])

            var navController= Navigation.findNavController(it)
            val bundle= bundleOf("json_check" to json_check)
            navController.navigate(R.id.nav_nuevo_check, bundle)
        }

        viewHolder.txtPlacas.text = dataSet[position]?.placas_unidad.toString()

       viewHolder.txtNombre.text = dataSet[position]?.nombre_personal.toString()

      /*  val dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))*/

        viewHolder.txtHora.text = dataSet[position]?.created_at.toString()



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}