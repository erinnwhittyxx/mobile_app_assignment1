package shipping.track.views

import javafx.application.Platform
import javafx.geometry.Orientation
import shipping.track.controllers.VesselUIController
import tornadofx.*

class MenuScreen : View("Vessel Tracking Main Menu") {

    val vesselUIController: VesselUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Vessel") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        vesselUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Vessels") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        vesselUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}