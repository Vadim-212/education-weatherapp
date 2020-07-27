package kz.step.weatherapp.presentation.base

interface BaseContract {
    interface BaseView {
        fun initializeViews()

        fun initializeListeners()

        fun initializeArguments()

        fun initializeDependencies()
    }
    interface BasePresenter<T> {
        fun attach(view: T)

        fun onStop()
    }
}