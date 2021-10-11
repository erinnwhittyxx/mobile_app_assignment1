package shipping.track.controllers

import mu.KotlinLogging
import shipping.track.models.*
import shipping.track.views.VesselView

class VesselController {

    val vessels = VesselMemStore()
    val vesselView = VesselView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Vessel Tracking App" }
        println("Vessel Tracking App Version 1.0")
    }

    fun menu() :Int { return vesselView.menu() }

    fun add(){
        var aVessel = VesselModel()

        if (vesselView.addVesselData(aVessel))
            vessels.create(aVessel)
        else
            logger.info("Vessel Not Added")
    }

    fun list() {
        vesselView.listVessels(vessels)
    }

    fun update() {

        vesselView.listVessels(vessels)
        var searchId = vesselView.getId()
        val aVessel = search(searchId)

        if(aVessel != null) {
            if(vesselView.updateVesselData(aVessel)) {
                vessels.update(aVessel)
                vesselView.showVessel(aVessel)
                logger.info("Vessel Updated : [ $aVessel ]")
            }
            else
                logger.info("Vessel Not Updated")
        }
        else
            println("Vessel Not Updated...")
    }

    fun search() {
        val aVessel = search(vesselView.getId())!!
        vesselView.showVessel(aVessel)
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
}