package shipping.track.views

import shipping.track.models.VesselModel
import shipping.track.models.VesselMemStore

class VesselView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Vessel")
        println(" 2. Update Vessel")
        println(" 3. List All Vessels")
        println(" 4. Search Vessels")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listVessels(vessels : VesselMemStore) {
        println("List All Vessels")
        println()
        vessels.logAll()
        println()
    }

    fun showVessel(vessel : VesselModel) {
        if(vessel != null)
            println("Vessel Details [ $vessel ]")
        else
            println("Vessel Not Found...")
    }

    fun addVesselData(vessel: VesselModel) : Boolean {

        println()
        print("Enter  : ")
        vessel.name = readLine()!!
        print("Enter a Description : ")
        vessel.arrivalTime = readLine()!!

        return vessel.name.isNotEmpty() && vessel.arrivalTime.isNotEmpty()
    }

    fun updateVesselData(vessel: VesselModel) : Boolean {

        var tempName: String?
        var tempArrivalTime: String?

        if (vessel != null) {
            print("Enter a new Name for [ " + vessel.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Arrival Time for [ " + vessel.arrivalTime + " ] : ")
            tempArrivalTime = readLine()!!

            if (!tempName.isNullOrEmpty() && !tempArrivalTime.isNullOrEmpty()) {
                vessel.name = tempName
                vessel.arrivalTime = tempArrivalTime
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}