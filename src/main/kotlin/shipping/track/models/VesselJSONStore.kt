package shipping.track.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import shipping.track.helpers.exists
import shipping.track.helpers.write
import shipping.track.helpers.read
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "vessels.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<VesselModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class VesselJSONStore : VesselStore {

    var vessels = mutableListOf<VesselModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<VesselModel> {
        return vessels
    }

    override fun findOne(id: Long): VesselModel? {
        var foundVessel: VesselModel? = vessels.find { p -> p.id == id }
        return foundVessel
    }

    override fun findName(name: String): MutableList<VesselModel> {
        var foundVessel: VesselModel? = vessels.find { p -> p.name.contains(name)}
        for vessel in vessels:
    }

    override fun create(vessel: VesselModel) {
        vessel.id = generateRandomId()
        vessels.add(vessel)
        serialize()
    }

    override fun update(vessel: VesselModel) {
        var foundVessel = findOne(vessel.id!!)
        if (foundVessel != null) {
            foundVessel.name = vessel.name
            foundVessel.arrivalTime = vessel.arrivalTime
        }
        serialize()
    }

    override fun delete(vessel: VesselModel) {
        vessels.remove(vessel)
        serialize()
    }

    internal fun logAll() {
        vessels.forEach { println("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(vessels, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        vessels = Gson().fromJson(jsonString, listType)
    }
}