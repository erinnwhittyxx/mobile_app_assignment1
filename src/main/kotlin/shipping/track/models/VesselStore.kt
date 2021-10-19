package shipping.track.models

interface VesselStore {
    fun findAll(): List<VesselModel>
    fun findOne(id: Long): VesselModel?
    fun create(vessel: VesselModel)
    fun update(vessel: VesselModel)
    fun delete(vessel: VesselModel)
//    fun filter(vessel: VesselModel)
}