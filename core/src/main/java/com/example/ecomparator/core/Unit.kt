package com.example.ecomparator.core

import java.math.BigDecimal

class Unit(
    val m: Int = 0,
    val s: Int = 0,
    val kg: Int = 0,
    val A: Int = 0,
    val K: Int = 0,
    val mol: Int = 0,
    val cd: Int = 0,
    val prefix: BigDecimal = 1.toBigDecimal()
) {
    companion object {

    }

    val baseUnitComposition = listOf(m,s,kg,A,K,mol,cd)
    val coherent = prefix == 1.toBigDecimal()

    override fun toString(): String {
        return ""
    }
    val fullForm = "1"
}