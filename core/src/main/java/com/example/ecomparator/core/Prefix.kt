package com.example.ecomparator.core

import java.math.BigDecimal

class Prefix(val numericMultiplier: BigDecimal) {
    companion object {
        val NONE = Prefix(1.toBigDecimal())
        val KILO = Prefix(1000.toBigDecimal())
        val MEGA = Prefix(1000_000.toBigDecimal())
    }
}