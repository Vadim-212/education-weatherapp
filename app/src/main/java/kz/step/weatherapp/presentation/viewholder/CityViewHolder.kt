package kz.step.weatherapp.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_city.view.*

class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun initiateBind(city: String, country: String) {
        itemView.textview_viewholder_city_city.setText(city)
        itemView.textview_viewholder_city_country.setText(country)
    }
}