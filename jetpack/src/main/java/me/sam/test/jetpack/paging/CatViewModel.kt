package me.sam.test.jetpack.paging

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import me.sam.test.jetpack.paging.datasource.CatDataSourceFactory

/**
 * desc:
 * date: 2020/05/13 0013
 * @author: dongsen
 */
class CatViewModel : ViewModel() {
    val catLiveData = CatDataSourceFactory().toLiveData(1)

    fun request(){
        catLiveData.value?.dataSource?.invalidate()
    }
}