package kz.step.weatherapp.presentation.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kz.step.weatherapp.R
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.presentation.adapter.MainActivityAdapter
import kz.step.weatherapp.presentation.contract.MainActivityContract
import kz.step.weatherapp.presentation.fragment.AddCityFragment
import kz.step.weatherapp.presentation.fragment.WeatherFragment
import kz.step.weatherapp.presentation.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    var presenter: MainActivityPresenter? = null
    var adapter: MainActivityAdapter? = null
    var cities: ArrayList<CityInWeatherList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        initializeViews()
        initializePresenter()
        initializeLayoutManager()
        presenter?.initializeData()
        initializeAdapter()
        initializeListeners()
        Log.d("sadfsagfqwe", this.cities.size.toString())
    }

    override fun initializePresenter() {
        presenter = MainActivityPresenter(this)
        presenter?.attach(this)
    }

    override fun initializeLayoutManager() {
        recyclerview_activity_main.layoutManager = LinearLayoutManager(this)
    }

    override fun initializeAdapter() {
        adapter = MainActivityAdapter(this, cities, presenter!!)
        recyclerview_activity_main.adapter = adapter
    }

    override fun initializeUpdateAdapter() {
        adapter?.notifyDataSetChanged()
    }

    override fun processData(cities: ArrayList<CityInWeatherList>) {
        this.cities.clear()
        this.cities.addAll(cities)
    }

    override fun initializeViews() { }

    override fun initializeListeners() {
        button_activity_main_add_location.setOnClickListener{
            val fragment = AddCityFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            supportFragmentManager.executePendingTransactions()
            fragmentTransaction.add(
                R.id.relativelayout_activity_main,
                fragment,
                fragment.javaClass.name)

            fragmentTransaction.addToBackStack("Name")
            fragmentTransaction.commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            presenter?.initializeData()
        }
    }

    override fun initializeArguments() { }

    override fun initializeDependencies() { }
}