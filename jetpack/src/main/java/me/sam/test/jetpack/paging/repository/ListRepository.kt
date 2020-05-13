package me.sam.test.jetpack.paging.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.sam.test.jetpack.paging.model.CatModel
import me.sam.test.jetpack.paging.net.RetrofitClient

/**
 * desc: data repository
 * date: 2020/05/12 0012
 * @author: dongsen
 */
class ListRepository {

    fun getCatList(page: Int, limit: Int = 10, order: String = "desc"):Observable<List<CatModel>> {
        return RetrofitClient.instance
                .getCatsList(page, limit, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}