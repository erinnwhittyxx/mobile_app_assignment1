package shipping.track.controllers


import mu.KotlinLogging
import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselModel
import shipping.track.views.VesselView

class VesselController {

    fun start(){
        logger.info { "Launching" }
        println("Vessel Tracking Version 1.0")

        var input: Int

        do {
            input = vesselView.menu()                                               //Loops menu until user input/selection is -1 (exit)
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                6 -> filterByName()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Vessel Tracking" }
    }

    val vessels = VesselJSONStore()                                                 //Create vessel list
    val vesselView = VesselView()                                                   //Connects user to controller
    val logger = KotlinLogging.logger {}                                            //Logging/Debugging

    fun add(){                                                                      //Add
        var aVessel = VesselModel()

        if (vesselView.addVesselData(aVessel))
            vessels.create(aVessel)
        else
            logger.info("Vessel Not Added")
    }

    fun list() {                                                                    //Lists All Vessels
        vesselView.listVessels(vessels)
    }

    fun update() {                                                                  //Updates

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

    fun delete() {                                                                  //Delete
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

    fun filterByName() {                                                            //Calls method in VesselView to pass vessels list
        vesselView.filteredByVesselName(vessels)
    }

    private fun search() {
        val aVessel = search(vesselView.getId())!!
        vesselView.showVessel(aVessel)
    }

    fun search(id: Long) : VesselModel? {
        var foundVessel = vessels.findOne(id)
        return foundVessel
    }

    fun dummyData() {
        vessels.create(VesselModel(name = "Adamoon", arrivalTime = "17:00", draught = 8.1))
        vessels.create(VesselModel(name= "Plato", arrivalTime = "06:09", draught = 5.1))
        vessels.create(VesselModel(name = "X Press Agility", arrivalTime = "02:00", draught = 8.1))
    }
}