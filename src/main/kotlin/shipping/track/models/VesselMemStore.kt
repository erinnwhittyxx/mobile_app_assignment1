package shipping.track.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class VesselMemStore : VesselStore {

    val vessels = ArrayList<VesselModel>()
    var filteredName = mutableListOf<VesselModel>()

    override fun findAll(): List<VesselModel> {
        return vessels
    }

    override fun findOne(id: Long) : VesselModel? {
        var foundVessel: VesselModel? = vessels.find { p -> p.id == id }
        return foundVessel
    }

    override fun create(vessel: VesselModel) {
        vessel.id = getId()
        vessels.add(vessel)
        logAll()
    }

    override fun update(vessel: VesselModel) {
        var foundVessel = findOne(vessel.id!!)
        if (foundVessel != null) {
            foundVessel.name = vessel.name
            foundVessel.arrivalTime = vessel.arrivalTime
        }
    }

    override fun delete(vessel: VesselModel) {
        vessels.remove(vessel)
    }

    override fun filterByName(name: String) :MutableList<VesselModel> {
        vessels.forEach{
            if(it.name.contains(name)){
                filteredName.add(it)
            }
        }
        return filteredName
    }

    internal fun logAll() {
        vessels.forEach { logger.info("$it") }
    }
}