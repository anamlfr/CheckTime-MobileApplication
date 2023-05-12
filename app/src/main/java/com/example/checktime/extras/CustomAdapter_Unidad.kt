package com.example.app_checktime.extras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.checktime.R
import com.google.gson.Gson

class CustomAdapter_Unidad(private val dataSet: Array<Modelos.Unidad>) :
    RecyclerView.Adapter<CustomAdapter_Unidad.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCve_unidad: TextView
        val txtPlacas_unidad: TextView

        init {
            // Define click listener for the ViewHolder's View.
            txtCve_unidad = view.findViewById(R.id.txtCve_unidad)
            txtPlacas_unidad = view.findViewById(R.id.txtPlacas_unidad)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_unidad, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemView.setOnClickListener{

            var objGson= Gson()
            var json_unidad = objGson.toJson(dataSet[position])

            var navController= Navigation.findNavController(it)
            val bundle= bundleOf("json_unidad" to json_unidad)
            navController.navigate(R.id.nav_nueva_unidad, bundle)
        }

        viewHolder.txtCve_unidad.text = dataSet[position]?.cve_unidad
        //val formatter: NumberFormat = DecimalFormat("$#,##0.00")

        viewHolder.txtPlacas_unidad.text = dataSet[position]?.placas_unidad


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}