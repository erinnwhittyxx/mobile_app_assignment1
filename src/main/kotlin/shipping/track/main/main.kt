package shipping.track.main

import mu.KotlinLogging
import shipping.track.controllers.VesselController
import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselMemStore
import shipping.track.views.VesselView

private val logger = KotlinLogging.logger {}

val vessels = VesselJSONStore()

fun main(args: Array<String>) {
    VesselController().start()
}