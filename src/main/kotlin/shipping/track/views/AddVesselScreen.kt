package shipping.track.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import shipping.track.controllers.VesselUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddVesselScreen : View("Add Vessel") {
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _arrivalTime = model.bind { SimpleStringProperty() }
    val vesselUIController: VesselUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            field("Arrival Time") {
                textarea(_arrivalTime).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        vesselUIController.add(_name.toString(),_arrivalTime.toString())

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
        model.clearDecorators()
    }
}