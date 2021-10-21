package shipping.track.views

import tornadofx.*
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import shipping.track.controllers.VesselUIController


class UpdateVesselScreen : View("Update Vessel") {
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _arrivalTime = model.bind { SimpleStringProperty() }
    val _draught = model.bind { SimpleDoubleProperty() }

    val vesselUIController: VesselUIController by inject()


    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            field("Arrival Time") {
                textfield(_arrivalTime).required()
            }
            field("Draught") {
                textfield(_draught).required()
            }
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        vesselUIController.add(_name.toString(),_arrivalTime.toString(),_draught.getValue().toDouble())
                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        vesselUIController.closeAdd()
                    }
                }
            }
        }
    }
    override fun onDock() {
        _name.value = ""
        _arrivalTime.value = ""
        _draught.value = 0.0;
        model.clearDecorators()
    }
}