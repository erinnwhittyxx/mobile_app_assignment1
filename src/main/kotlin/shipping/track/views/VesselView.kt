package shipping.track.views

import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselModel


class VesselView {

    fun menu() : Int {

        var option : Int
        var input: String?

        val RESET = "\u001b[0m"
        val PURPLE_BACKGROUND = "\u001b[45m"
        val BLUE_BACKGROUND = "\u001B[44m"
        val TEXT_BLACK = "\u001B[30m"
        val TEXT_BLUE = "\u001B[34m"
        val BLACK_BOLD = "\u001B[1;30m"

        println( BLUE_BACKGROUND + BLACK_BOLD + "MAIN MENU" + RESET)
        println(TEXT_BLUE + " 1. Add Vessel")
        println(" 2. Update Vessel")
        println(" 3. List All Vessels")
        println(" 4. Search Vessels")
        println(" 5. Delete Vessel")
//        println(" 6. Filter Vessels")
        println("-99. For Dummy Data")
        println("-1. Exit" + RESET)
        println()
        print(PURPLE_BACKGROUND + TEXT_BLACK + "Enter Option : " + RESET)
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listVessels(vessels : VesselJSONStore) {
        println("List All Vessels")
        println()
        vessels.logAll()
        println()
    }

    fun showVessel(vessel : VesselModel) {
        if(vessel != null)
            println("Vessel Details [ $vessel ]")
        else
            println("Vessel Not Found")
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
        var strId : String?
        var searchId : Long
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}