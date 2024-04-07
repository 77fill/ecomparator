package com.example.ecomparator.core

import java.lang.IllegalStateException
import java.math.BigDecimal

val orderedBaseUnits = listOf("m","s","kg","A","K","mol","cd")

class Unit(
    val m: Int = 0,
    val s: Int = 0,
    val kg: Int = 0,
    val A: Int = 0,
    val K: Int = 0,
    val mol: Int = 0,
    val cd: Int = 0,
    numericMultiplier: BigDecimal = 1.toBigDecimal(),
    val prefix: Prefix = Prefix.NONE,
    val name: String = "",
    val fixedQuantityTypes: List<QuantityType> = listOf()
) {
    val specifiedNumericMultiplier = numericMultiplier
    val numericMultiplier: BigDecimal
        get() =
            if (specifiedNumericMultiplier == 1.toBigDecimal())
                prefix.numericMultiplier
            else
                specifiedNumericMultiplier

    val baseUnitExponents = mapOf(
        "m" to m,
        "s" to s,
        "kg" to kg,
        "A" to A,
        "K" to K,
        "mol" to mol,
        "cd" to cd
    )

    companion object {
        val METER = Unit(m=1, name = "Metre")
        val METRE = METER
        val SECOND = Unit(s=1, name = "Second")
        val KILOGRAM = Unit(kg=1, name = "Kilogram")
        val AMPERE = Unit(A=1, name = "Ampere")
        val KELVIN = Unit(K=1, name = "Kelvin")
        val MOLE = Unit(mol=1, name = "Mole")
        val CANDELA = Unit(cd=1, name = "Candela")
    }

    object SPECIAL {
        val RADIAN = Unit(name = "rad", fixedQuantityTypes = listOf(SpecialQuantityType.PLANE_ANGLE))
        val STERADIAN = Unit(name = "sr", fixedQuantityTypes = listOf(SpecialQuantityType.SOLID_ANGLE))
        val HERTZ = Unit(name = "Hz", s=-1, fixedQuantityTypes = listOf(SpecialQuantityType.FREQUENCY))
        val NEWTON = Unit(name = "N", kg=1, m=1, s=-2, fixedQuantityTypes = listOf(SpecialQuantityType.FORCE, SpecialQuantityType.WEIGHT))
        val WATT = Unit(name = "W", kg=1, m=2, s=-3, fixedQuantityTypes = listOf(SpecialQuantityType.POWER, SpecialQuantityType.RADIANT_FLUX))
    }

    val baseUnitComposition = listOf(m,s,kg,A,K,mol,cd)
    val coherent = numericMultiplier == 1.toBigDecimal()
    val baseUnit = coherent && baseUnitComposition.filter{it == 1}.size == 1
    val isSpecial = fixedQuantityTypes.isNotEmpty()

    override fun toString(): String {
        return orderedBaseUnits
            .map { it to baseUnitExponents[it] }
            .map {
                when (it.second) {
                    1 -> it.first
                    0 -> ""
                    null -> throw IllegalStateException("baseUnitExponents must have baseUnitOrder elements as keys!")
                    else -> it.first + "^" + it.second
                }
            }
            .filter {it != ""}
            .joinToString("*")
            .let {
                if(numericMultiplier == 1.toBigDecimal())
                    it
                else
                    numericMultiplier.toString() + "*" +it
            }
    }
    val fullForm = if(toString()=="") "1" else toString()

    fun withPrefix(prefix: Prefix) : Unit {
        return Unit(m=this.m,s=this.s,kg=this.kg,A=this.A,K=this.K,mol=this.mol,cd=this.cd, prefix=prefix)
    }
}