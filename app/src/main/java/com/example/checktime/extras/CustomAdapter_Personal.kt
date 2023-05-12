package com.example.checktime.extras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.app_checktime.extras.CustomAdapter_Unidad
import com.example.app_checktime.extras.Modelos
import com.example.checktime.R
import com.google.gson.Gson

class CustomAdapter_Personal(private val dataSet: Array<Modelos.Personal>) :
    RecyclerView.Adapter<CustomAdapter_Personal.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCve_personal: TextView
        val txtNombre_personal: TextView

        init {
            // Define click listener for the ViewHolder's View.
            txtCve_personal = view.findViewById(R.id.txtCve_personal)
            txtNombre_personal = view.findViewById(R.id.txtNombre_personal)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_personal, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemView.setOnClickListener{

            var objGson= Gson()
            var json_personal = objGson.toJson(dataSet[position])

            var navController= Navigation.findNavController(it)
            val bundle= bundleOf("json_personal" to json_personal)
            navController.navigate(R.id.nav_nuevo_personal, bundle)
        }

        viewHolder.txtCve_personal.text = dataSet[position]?.cve_personal

        viewHolder.txtNombre_personal.text = dataSet[position]?.nombre_personal


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}