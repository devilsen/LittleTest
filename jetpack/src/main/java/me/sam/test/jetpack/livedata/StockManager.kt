package me.sam.test.jetpack.livedata

import java.math.BigDecimal

class StockManager {

    private val listeners = ArrayList<(BigDecimal) -> Unit>()

    fun requestPriceUpdates(listener: (BigDecimal) -> Unit) {
        listeners.add(listener)
    }

    fun removeUpdates(listener: (BigDecimal) -> Unit) {
        listeners.remove(listener)
    }

}
