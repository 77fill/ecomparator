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
    val prefix: BigDecimal = 1.toBigDecimal(),
    val name: String = ""
) {
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

    val baseUnitComposition = listOf(m,s,kg,A,K,mol,cd)
    val coherent = prefix == 1.toBigDecimal()
    val baseUnit = coherent && baseUnitComposition.filter{it == 1}.size == 1

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
                if(prefix == 1.toBigDecimal())
                    it
                else
                    prefix.toString() + "*" +it
            }
    }
    val fullForm = if(toString()=="") "1" else toString()
}