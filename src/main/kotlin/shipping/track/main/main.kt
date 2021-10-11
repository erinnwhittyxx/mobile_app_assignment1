package shipping.track.main

import mu.KotlinLogging
import shipping.track.controllers.VesselController
import shipping.track.models.VesselMemStore
import shipping.track.models.VesselModel
import shipping.track.views.VesselView


private val logger = KotlinLogging.logger {}

var vessels = VesselMemStore()
val VesselView = VesselView()
val controller = VesselController()

fun main(args: Array<String>) {
    logger.info { "Launching VesselTracker App" }
    println("VesselTracker 1.0")

    var input: Int

    do {
        input = VesselView.menu()
        when(input) {
            1 -> addVessel()
            2 -> updateVessel()
            3 -> VesselView.listVessels(vessels)
            4 -> searchVessel()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Closing VesselTracker App" }
}

fun addVessel(){
    var aVessel = VesselModel()

    if (VesselView.addVesselData(aVessel))
        vessels.create(aVessel)
    else
        logger.info("Vessel Not Added")
}

fun updateVessel() {
    VesselView.listVessels(vessels)
    var searchId = VesselView.getId()
    val aVessel = search(searchId)

    if(aVessel != null) {
        if(VesselView.updateVesselData(aVessel)) {
            vessels.update(aVessel)
            VesselView.showVessel(aVessel)
            logger.info("Vessel Updated : [ $aVessel ]")
        }
        else
            logger.info("Vessel Not Updated")
    }
    else
        println("Vessel Not Updated...")
}

fun searchVessel() {
    val aVessel = search(VesselView.getId())!!
    VesselView.showVessel(aVessel)
}

fun search(id: Long) : VesselModel? {
    var foundVessel = vessels.findOne(id)
    return foundVessel
}

fun dummyData() {
    vessels.create(VesselModel(name = "Samskip Express", arrivalTime = "18:00"))
    vessels.create(VesselModel(name= "Arklow Dawn", arrivalTime = "04:00"))
    vessels.create(VesselModel(name = "Arklow Dusk", arrivalTime = "13:50"))
}