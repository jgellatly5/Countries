package com.jordangellatly.countries.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.countries.R
import com.jordangellatly.countries.api.Country

class CountriesAdapter(
    private val context: Context,
    private val countriesList: List<Country>
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameRegion: TextView = view.findViewById(R.id.nameRegion)
        val code: TextView = view.findViewById(R.id.code)
        val capital: TextView = view.findViewById(R.id.capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countriesList[position]
        holder.nameRegion.text = context.getString(R.string.name_region_placeholder, country.name, country.region)
        holder.code.text = country.code
        holder.capital.text = country.capital
    }

    override fun getItemCount() = countriesList.size
}
