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

val JSON_FILE = "vessels.json"                                                          //Sets location file
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<VesselModel>>() {}.type

fun generateRandomId(): Long {                                                          //Generates random ID number
    return Random().nextLong()
}

class VesselJSONStore : VesselStore {

    var vessels = mutableListOf<VesselModel>()                                          //Vessels ArrayList
    var filteredName = mutableListOf<VesselModel>()                                     //Filtered By Name ArrayList

    init {
        if (exists(JSON_FILE)) {                                                        //If file is present -> convert data
            deserialize()                                                               //to runtime objects
        }
    }

    override fun findAll(): MutableList<VesselModel> {                                  //Returns all vessels in ArrayList
        return vessels
    }

    override fun findOne(id: Long): VesselModel? {                                      //Finds vessel from ID search
        var foundVessel: VesselModel? = vessels.find { p -> p.id == id }
        return foundVessel
    }

    override fun create(vessel: VesselModel) {                                           //Assigns random ID and adds new Vessel
        vessel.id = generateRandomId()                                                   //to list
        vessels.add(vessel)
        serialize()
    }

    override fun update(vessel: VesselModel) {                                            //Finds vessel by ID and adds updated parameters
        var foundVessel = findOne(vessel.id!!)
        if (foundVessel != null) {
            foundVessel.name = vessel.name
            foundVessel.arrivalTime = vessel.arrivalTime
            foundVessel.draught = vessel.draught
        }
        serialize()
    }

    override fun delete(vessel: VesselModel) {                                            //Deletes vessel from ArrayList
        vessels.remove(vessel)
        serialize()
    }

    override fun filterByName(name: String) :MutableList<VesselModel> {                   //Iterates over the full list of vessels, and if
        filteredName.clear()
        vessels.forEach{
            if(it.name.contains(name)){                                                   //a vessels name contains the name searched for it
                filteredName.add(it)                                                      //adds it to the list
            }
        }
        return filteredName
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