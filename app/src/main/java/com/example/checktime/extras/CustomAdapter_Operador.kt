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
import kotlinx.coroutines.selects.select
import org.w3c.dom.Text

class CustomAdapter_Operador(private val dataSet: Array<Modelos.Operador>) :
    RecyclerView.Adapter<CustomAdapter_Operador.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtId_personal: TextView
        val txtId_unidad: TextView
        val txtstatus_operador: TextView

        init {
            // Define click listener for the ViewHolder's View.
            txtId_personal = view.findViewById(R.id.txtIdPersonal)
            txtId_unidad = view.findViewById(R.id.txtIdUnidad)
            txtstatus_operador = view.findViewById(R.id.txtStatus)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_operador, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemView.setOnClickListener{

            var objGson= Gson()
            var json_operador = objGson.toJson(dataSet[position])

            var navController= Navigation.findNavController(it)
            val bundle= bundleOf("json_operador" to json_operador)
            navController.navigate(R.id.nav_nuevo_operador, bundle)
        }

        viewHolder.txtId_personal.text = dataSet[position]?.nombre_personal.toString()

        viewHolder.txtId_unidad.text = dataSet[position]?.placas_unidad.toString()

        viewHolder.txtstatus_operador.text = dataSet[position]?.status_operador

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}