package me.sam.test.jetpack.paging.net

import io.reactivex.Observable
import me.sam.test.jetpack.paging.model.CatModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiStore {

    @GET("/v1/images/search")
    fun getCatsList(@Query("page") page: Int, @Query("limit") limit: Int = 10,@Query("order") order: String = "desc"):Observable<List<CatModel>>

}
