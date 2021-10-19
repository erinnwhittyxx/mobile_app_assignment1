package shipping.track.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import shipping.track.controllers.VesselUIController
import shipping.track.models.VesselModel
import tornadofx.*

class ListVesselScreen : View("List Vessels") {

    val vesselUIController: VesselUIController by inject()
    val tableContent = vesselUIController.vessels.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", VesselModel::id)
            readonlyColumn("NAME", VesselModel::name)
            readonlyColumn("ARRIVAL TIME", VesselModel::arrivalTime)
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