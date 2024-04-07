package com.example.ecomparator.core

import org.junit.Assert
import org.junit.Test

class UnitTestSimple {
    @Test
    fun empty_unit_is_1() {
        val unit = Unit()
        Assert.assertTrue(unit.baseUnitComposition == listOf(0,0,0,0,0,0,0))
        Assert.assertTrue(unit.coherent && unit.numericMultiplier == 1.toBigDecimal())
        Assert.assertTrue(unit.toString() == "")
        Assert.assertTrue(unit.fullForm == "1")
    }

    @Test
    fun several_made_up_units() {
        var unit: Unit

        unit = Unit(s=1,m=-2,K=2)
        Assert.assertTrue(unit.baseUnitComposition == listOf(-2,1,0,0,2,0,0))
        Assert.assertTrue(unit.coherent && unit.numericMultiplier == 1.toBigDecimal())
        Assert.assertTrue(unit.toString() == "m^-2*s*K^2")
        Assert.assertTrue(unit.toString() == unit.fullForm)

        unit = Unit(numericMultiplier = 200_000.2.toBigDecimal(), kg=1, m=1, s=-2)
        Assert.assertTrue(unit.baseUnitComposition == listOf(1,-2,1,0,0,0,0))
        Assert.assertTrue(!unit.coherent && unit.numericMultiplier == 200_000.2.toBigDecimal())
        Assert.assertTrue(unit.toString() == "200000.2*m*s^-2*kg")
        Assert.assertTrue(unit.toString() == unit.fullForm)
    }

    private fun check_base_unit(unit: Unit): Boolean {
        return unit.let {
            it.baseUnit
                    && it.coherent
                    && it.numericMultiplier == 1.toBigDecimal()
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

    private fun check_special_derived_unit(unit: Unit): Boolean {
        return !unit.baseUnit
                && unit.coherent
                && unit.numericMultiplier == 1.toBigDecimal()
    }

    @Test
    fun check_special_derived_units() {
        Assert.assertTrue(Unit.SPECIAL.RADIAN.baseUnitComposition == listOf(0,0,0,0,0,0,0))
        Assert.assertTrue(Unit.SPECIAL.STERADIAN.baseUnitComposition == listOf(0,0,0,0,0,0,0))
        Assert.assertTrue(Unit.SPECIAL.HERTZ.baseUnitComposition == listOf(0,-1,0,0,0,0,0))
        Assert.assertTrue(Unit.SPECIAL.NEWTON.baseUnitComposition == listOf(1,-2,1,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.PASCAL.baseUnitComposition == listOf(-1,-2,1,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.JOULE.baseUnitComposition == listOf(2,-2,1,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.WATT.baseUnitComposition == listOf(2,-3,1,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.COULOMB.baseUnitComposition == listOf(0,1,0,0,1,0,0))
//        Assert.assertTrue(Unit.SPECIAL.VOLT.baseUnitComposition == listOf(2,-3,1,0,-1,0,0))
//        Assert.assertTrue(Unit.SPECIAL.FARAD.baseUnitComposition == listOf(-2,4,-1,0,2,0,0))
//        Assert.assertTrue(Unit.SPECIAL.OHM.baseUnitComposition == listOf(2,-3,1,0,-2,0,0))
//        Assert.assertTrue(Unit.SPECIAL.SIEMENS.baseUnitComposition == listOf(-2,3,-1,0,2,0,0))
//        Assert.assertTrue(Unit.SPECIAL.WEBER.baseUnitComposition == listOf(2,-2,1,0,-1,0,0))
//        Assert.assertTrue(Unit.SPECIAL.TESLA.baseUnitComposition == listOf(0,-2,1,0,-1,0,0))
//        Assert.assertTrue(Unit.SPECIAL.HENRY.baseUnitComposition == listOf(2,-2,1,0,-2,0,0))
//        Assert.assertTrue(Unit.SPECIAL.DEGREE_CELSIUS.baseUnitComposition == listOf(0,0,0,1,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.LUMEN.baseUnitComposition == listOf(0,0,0,0,0,0,1))
//        Assert.assertTrue(Unit.SPECIAL.LUX.baseUnitComposition == listOf(-2,0,0,0,0,0,1))
//        Assert.assertTrue(Unit.SPECIAL.BECQUEREL.baseUnitComposition == listOf(0,-1,0,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.GRAY.baseUnitComposition == listOf(2,-2,0,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.SIEVERT.baseUnitComposition == listOf(2,-2,0,0,0,0,0))
//        Assert.assertTrue(Unit.SPECIAL.KATAL.baseUnitComposition == listOf(0,-1,0,0,0,1,0))
    }

    @Test
    fun check_several_prefixes() {
        Assert.assertTrue(Unit(prefix = Prefix.KILO, m=1).let {
            it.baseUnitComposition == listOf(1,0,0,0,0,0,0)
                    && it.numericMultiplier == 1000.toBigDecimal()
        })
        Assert.assertTrue(Unit.SPECIAL.WATT.withPrefix(Prefix.MEGA).let {
            it.baseUnitComposition == listOf(2,-3,1,0,0,0,0)
                    && it.numericMultiplier == 1000_000.toBigDecimal()
        })
    }
}