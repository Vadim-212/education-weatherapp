package kz.step.weatherapp.presentation.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add_city.*
import kz.step.weatherapp.R
import kz.step.weatherapp.data.City
import kz.step.weatherapp.domain.usecase.CityUseCase
import kz.step.weatherapp.presentation.adapter.CityAdapter
import kz.step.weatherapp.presentation.contract.CityFragmentContract
import kz.step.weatherapp.presentation.presenter.CityFragmentPresenter

class AddCityFragment : Fragment(), CityFragmentContract.View {
    var rootView: View? = null
    var presenter: CityFragmentPresenter? = null
    var adapter: CityAdapter? = null
    var cities: ArrayList<City> = ArrayList()
    var cityUseCase = CityUseCase() // TODO: dagger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = LayoutInflater.from(context).inflate(R.layout.fragment_add_city, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializePresenter()
        initializeLayoutManager()
        //presenter?.initializeData()
        initializeAdapter()
        initializeListeners()
    }

    override fun initializePresenter() {
        presenter = context?.let { CityFragmentPresenter(it) }
        presenter?.attach(this)
    }

    override fun initializeLayoutManager() {
        recyclerview_fragment_add_city.layoutManager = LinearLayoutManager(context)
        recyclerview_fragment_add_city.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
    }

    override fun initializeAdapter() {
        adapter = CityAdapter(requireContext(), cities, presenter!!)
        recyclerview_fragment_add_city.adapter = adapter
    }

    override fun initializeUpdateAdapter() {
        adapter?.notifyDataSetChanged()
    }

    override fun processData(cities: ArrayList<City>) {
        this.cities.clear()
        this.cities.addAll(cities)
    }

    override fun initializeViews() { }

    override fun initializeListeners() {
        edittext_fragment_add_city_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.isEmpty()) {
                    presenter?.clearData()
                } else {
                    presenter?.updateDataByQuery(s.toString())
                }
            }
        })

        imageview_fragment_add_city_clear_search.setOnClickListener {
            edittext_fragment_add_city_search.setText("")
            presenter?.clearData()
        }
    }

    override fun initializeArguments() { }

    override fun initializeDependencies() { }


}