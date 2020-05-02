package me.sam.test.jetpack.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * desc:
 * date: 2020/04/28 0028
 * @author: dongsen
 */
internal class MyViewModel : ViewModel() {

    val number: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    fun add() {
        number.value = number.value?.plus(1)
    }

}