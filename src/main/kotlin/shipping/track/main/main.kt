package shipping.track.main

import mu.KotlinLogging
import shipping.track.controllers.VesselController
import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselMemStore
import shipping.track.views.VesselView

private val logger = KotlinLogging.logger {}

//var vessels = VesselMemStore()
val vessels = VesselJSONStore()
val VesselView = VesselView()
val controller = VesselController()

fun main(args: Array<String>) {
    VesselController().start()
}