package shipping.track.controllers

import kotlin.random.Random
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselModel

class VesselControllerTest {

    val VesselStore = VesselJSONStore()

    @Test
    fun findAllTest() {
        val test1 = VesselModel(0,"Samskip","18:00", 6.0)
        VesselStore.create(test1)
        assertEquals(VesselStore.findAll().size, VesselStore.vessels.size)
    }

    @Test
    fun findOneTest(){
        val test1 = VesselModel(name = "Samskip Express", arrivalTime = "18:00", draught = 6.0)
        VesselStore.create(test1)
        assertEquals(
            VesselStore.vessels[VesselStore.vessels.size - 1],
            VesselStore.findOne(VesselStore.vessels[VesselStore.vessels.size - 1].id)
        )
    }

    @Test
    fun createTest(){
        val test1 = VesselModel(0,"Arklow Dawn", "20:30",6.4)
        VesselStore.create(test1)
        assertTrue(VesselStore.vessels.contains(test1))
    }

    @Test
    fun updateTest(){
        val updating = VesselStore.vessels[0]
        val newDraught = Random.nextDouble()
        updating.draught = newDraught
        VesselStore.update(updating)
        assertEquals(updating.draught, newDraught)
    }

    @Test
    fun deleteOneTest(){
        val deleting = VesselStore.vessels[0]
        VesselStore.delete(deleting)
        assertFalse(VesselStore.vessels.contains(deleting))
    }
}
