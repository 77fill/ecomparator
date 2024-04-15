package dev.pschmalz.ecomparator.user_interactor

import dev.pschmalz.ecomparator.user_interactor.boundary.Dao
import dev.pschmalz.ecomparator.user_interactor.data.EntityType
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test


class TestListEntityTypes {

    val dao = mockk<Dao>()
    val entityTypeListing = EntityTypeListing(dao)

    @Test
    fun get_all_entity_types() {
        every {dao.getEntityTypeNames()} returns listOf("Country", "Device")
        every {dao.getQuantityTypeNamesForEntityTypeNames(eq(listOf("Country", "Device")))} returns
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