package me.sam.test.jetpack.livedata

import androidx.lifecycle.LiveData
import java.math.BigDecimal

class StockLiveData() : LiveData<BigDecimal>() {

    private val stockManager = StockManager()

    private val listener = { price: BigDecimal -> value = price }

    override fun onActive() {
        super.onActive()
        stockManager.requestPriceUpdates(listener)
    }

    override fun onInactive() {
        super.onInactive()
        stockManager.removeUpdates(listener)
    }

}