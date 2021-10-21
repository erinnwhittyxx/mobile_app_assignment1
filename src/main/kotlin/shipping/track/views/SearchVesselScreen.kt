package shipping.track.views

import shipping.track.controllers.VesselUIController
import shipping.track.models.VesselModel
import tornadofx.*


class SearchVesselScreen : View("Vessel Search Menu") {

    val vesselUIController: VesselUIController by inject()
    val tableContent = vesselUIController.vessels.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", VesselModel::id)
            readonlyColumn("NAME", VesselModel::name)
            readonlyColumn("ARRIVAL TIME", VesselModel::arrivalTime)
            readonlyColumn("DRAUGHT", VesselModel::draught)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    vesselUIController.closeList()
                }
            }
        }
    }



}