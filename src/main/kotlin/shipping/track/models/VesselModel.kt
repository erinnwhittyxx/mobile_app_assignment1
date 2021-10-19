package shipping.track.models

data class VesselModel(var id: Long = 0,
                       var name: String = "",
                       var arrivalTime: String = "",
                       var length: Int = 0)