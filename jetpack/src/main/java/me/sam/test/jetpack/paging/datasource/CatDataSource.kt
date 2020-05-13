package me.sam.test.jetpack.paging.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.sam.test.jetpack.paging.model.CatModel
import me.sam.test.jetpack.paging.net.RetrofitClient

/**
 * desc: paging data source
 * date: 2020/05/13 0013
 * @author: dongsen
 */
class CatDataSource : PageKeyedDataSource<Int, CatModel>() {

    private val tag = "CAT_DATA_SOURCE"

    private fun getCatList(page: Int): Observable<List<CatModel>> {
        return RetrofitClient.instance
                .getCatsList(page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CatModel>) {
        getCatList(1)
                .subscribe(object : Observer<List<CatModel>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<CatModel>) {
                        Log.e(tag, "onNext")

                        callback.onResult(t, null, 2)
                    }

                    override fun onComplete() {
                        Log.e(tag, "onComplete")
                    }

                    override fun onError(e: Throwable) {
                        Log.e(tag, "onError $e")
                    }
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CatModel>) {
        getCatList(params.key)
                .subscribe(object : Observer<List<CatModel>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<CatModel>) {
                        callback.onResult(t, params.key + 1)
                    }

                    override fun onComplete() {
                        Log.e(tag, "onComplete")
                    }

                    override fun onError(e: Throwable) {
                        Log.e(tag, "onError $e")
                    }
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CatModel>) {
    }
}