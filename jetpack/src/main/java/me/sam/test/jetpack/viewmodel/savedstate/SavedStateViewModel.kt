package me.sam.test.jetpack.viewmodel.savedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * desc:
 * date: 2020/04/28 0028
 * @author: dongsen
 */
internal class SavedStateViewModel(private var savedStateHandle: SavedStateHandle) : ViewModel() {

    private val numberKey = "NUMBER"

    val number: MutableLiveData<Int>
        get() {
            if (!savedStateHandle.contains(numberKey)) {
                savedStateHandle[numberKey] = 0
            }
            return savedStateHandle.getLiveData(numberKey)
        }

    fun add() {
        number.value = number.value?.plus(1)
    }
}