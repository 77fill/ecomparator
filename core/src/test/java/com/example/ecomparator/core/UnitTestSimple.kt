package com.example.ecomparator.core

import org.junit.Assert
import org.junit.Test

class UnitTestSimple {
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
        Assert.assertTrue(unit.toString() == "200000.2*m*s^-2*kg")
        Assert.assertTrue(unit.toString() == unit.fullForm)
    }

    private fun check_base_unit(unit: Unit): Boolean {
        return unit.let {
            it.baseUnit
                    && it.coherent
                    && it.prefix == 1.toBigDecimal()
        }
    }
    @Test
    fun check_base_units() {
        Assert.assertTrue(
            listOf(
                Unit.METER, Unit.SECOND, Unit.KILOGRAM,
                Unit.AMPERE, Unit.KELVIN, Unit.MOLE, Unit.CANDELA
            )
                .all { check_base_unit(it) }
        )
        Assert.assertTrue(Unit.METER.baseUnitComposition == listOf(1,0,0,0,0,0,0))
        Assert.assertTrue(Unit.SECOND.baseUnitComposition == listOf(0,1,0,0,0,0,0))
        Assert.assertTrue(Unit.KILOGRAM.baseUnitComposition == listOf(0,0,1,0,0,0,0))
        Assert.assertTrue(Unit.AMPERE.baseUnitComposition == listOf(0,0,0,1,0,0,0))
        Assert.assertTrue(Unit.KELVIN.baseUnitComposition == listOf(0,0,0,0,1,0,0))
        Assert.assertTrue(Unit.MOLE.baseUnitComposition == listOf(0,0,0,0,0,1,0))
        Assert.assertTrue(Unit.CANDELA.baseUnitComposition == listOf(0,0,0,0,0,0,1))
    }

    @Test
    fun check_base_unit_names() {
        Assert.assertTrue(Unit.METER.name == "Metre")
        Assert.assertTrue(Unit.SECOND.name == "Second")
        Assert.assertTrue(Unit.KILOGRAM.name == "Kilogram")
        Assert.assertTrue(Unit.AMPERE.name == "Ampere")
        Assert.assertTrue(Unit.KELVIN.name == "Kelvin")
        Assert.assertTrue(Unit.MOLE.name == "Mole")
        Assert.assertTrue(Unit.CANDELA.name == "Candela")
    }
}