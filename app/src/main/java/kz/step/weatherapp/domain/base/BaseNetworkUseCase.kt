package kz.step.weatherapp.domain.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseNetworkUseCase<T> {
    val compositeDisposable =CompositeDisposable()

    fun execute(disposableObserver: DisposableObserver<T>, query: String) {
        val observable = this.initiateCreateObservable(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val observer =observable.subscribeWith(disposableObserver)
        compositeDisposable.add(observer)
    }

    abstract fun initiateCreateObservable(query: String): Observable<T>

    fun clear() {
        compositeDisposable.clear()
    }
}