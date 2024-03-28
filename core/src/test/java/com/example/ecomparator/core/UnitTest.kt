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

    @Test
    fun several_made_up_units() {
        var unit: Unit

        unit = Unit(s=1,m=-2,K=2)
        Assert.assertTrue(unit.baseUnitComposition == listOf(-2,1,0,0,2,0,0))
        Assert.assertTrue(unit.coherent && unit.prefix == 1.toBigDecimal())
        Assert.assertTrue(unit.toString() == "m^-2*s*K^2")
        Assert.assertTrue(unit.toString() == unit.fullForm)

        unit = Unit(prefix = 200_000.2.toBigDecimal(), kg=1, m=1, s=-2)
        Assert.assertTrue(unit.baseUnitComposition == listOf(1,-2,1,0,0,0,0))
        Assert.assertTrue(!unit.coherent && unit.prefix == 200_000.2.toBigDecimal())
        Assert.assertTrue(unit.toString() == "m*s^-2*kg")
        Assert.assertTrue(unit.toString() == unit.fullForm)
    }
}