package shipping.track.controllers

import mu.KotlinLogging
//import shipping.track.main.controller
import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselModel
import shipping.track.views.VesselView

class VesselController {

    fun start(){
        logger.info { "Launching" }
        println("Vessel Tracking Version 1.0")

        var input: Int

        do {
            input = vesselView.menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
//                6 -> filter()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Vessel Tracking" }
    }

    val vessels = VesselJSONStore()
    val vesselView = VesselView()
    val logger = KotlinLogging.logger {}

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
            println("Vessel Not Updated")
    }

    fun delete() {
        vesselView.listVessels(vessels)
        var searchId = vesselView.getId()
        val aVessel = search(searchId)

        if(aVessel != null) {
            vessels.delete(aVessel)
            println("Vessel Deleted")
        }
        else
            println("Vessel Not Deleted")
    }

//    fun filter(){
//        vesselView.filterVessels(vessels)
//    }


    fun search() {
        val aVessel = search(vesselView.getId())!!
        vesselView.showVessel(aVessel)
    }

    fun search(id: Long) : VesselModel? {
        var foundVessel = vessels.findOne(id)
        return foundVessel
    }
}