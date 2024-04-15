package dev.pschmalz.ecomparator.user_interactor

import dev.pschmalz.ecomparator.user_interactor.boundary.Repository
import dev.pschmalz.ecomparator.user_interactor.data.EntityType
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test


class TestListEntityTypes {

    val repository = mockk<Repository>()
    val entityTypeListing = EntityTypeListing(repository)

    @Test
    fun get_all_entity_types() {
        every {repository.getEntityTypeNames()} returns listOf("Country", "Device")
        every {repository.getQuantityTypeNamesForEntityTypeNames(eq(listOf("Country", "Device")))} returns
                mapOf(
                    "Country" to listOf("Area"),
                    "Device" to listOf("Power Consumption", "Cost")
                )

        assertTrue(
            entityTypeListing.getEntityTypes() == listOf(
                EntityType("Country", listOf("Area")),
                EntityType("Device", listOf("Power Consumption", "Cost"))
            )
        )
    }
}