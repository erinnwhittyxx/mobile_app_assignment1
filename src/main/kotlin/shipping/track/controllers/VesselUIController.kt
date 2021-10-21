package shipping.track.controllers

import mu.KotlinLogging
import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselModel
import shipping.track.views.AddVesselScreen
import shipping.track.views.ListVesselScreen
import shipping.track.views.MenuScreen
import shipping.track.views.SearchVesselScreen
import tornadofx.*

class VesselUIController : Controller() {

    val vessels = VesselJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Vessel TornadoFX UI App" }
    }
    fun add(_name : String, _arrivalTime : String, _draught : Double){

        var aVessel = VesselModel(name = _name, arrivalTime = _arrivalTime, draught = _draught)
        vessels.create(aVessel)
        logger.info("Vessel Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListVesselScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        vessels.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddVesselScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(SearchVesselScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddVesselScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListVesselScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}