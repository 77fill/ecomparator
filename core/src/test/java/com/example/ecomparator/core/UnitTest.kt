package com.example.ecomparator.core

import org.junit.Assert
import org.junit.Test

class UnitTest {
    @Test
    fun empty_unit_is_1() {
        val unit = Unit()
        Assert.assertTrue(unit.baseUnitComposition == listOf(0,0,0,0,0,0,0))
        Assert.assertTrue(unit.coherent && unit.prefix == 1.toBigDecimal())
        Assert.assertTrue(unit.toString() == "")
        Assert.assertTrue(unit.fullForm == "1")
    }
}