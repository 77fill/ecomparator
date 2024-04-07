package dev.pschmalz.ecomparator.core

import java.time.LocalDate

class Entity(
    val type: EntityType,
    val name: String,
    val date: LocalDate,
    val quantities: Map<QuantityType, Quantity>
) {
}