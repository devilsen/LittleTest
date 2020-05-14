package me.sam.test.jetpack.paging.datasource

import androidx.paging.DataSource
import me.sam.test.jetpack.paging.model.CatModel

/**
 * desc: data source factory
 * date: 2020/05/13
 * @author: dongsen
 */
class CatDataSourceFactory : DataSource.Factory<Int, CatModel>() {
    override fun create(): DataSource<Int, CatModel> {
        return CatDataSource()
    }

}