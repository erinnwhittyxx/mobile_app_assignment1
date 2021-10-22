package shipping.track.views

import shipping.track.models.VesselJSONStore
import shipping.track.models.VesselModel
import java.util.*


class VesselView {

    val input = Scanner(System.`in`)

    fun menu() : Int {                                                                      //Terminal App Menu

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
        println(" 6. Filter Vessels By Name")
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

    fun listVessels(vessels : VesselJSONStore) {                                        //Listing Vessels if from VesselJSONStore
        println("List All Vessels")
        println()
        vessels.logAll()
        println()
    }

    fun showVessel(vessel : VesselModel) {                                              //Vessel must be a VesselModel to show
        if(vessel != null)
            println("Vessel Details [ $vessel ]")
        else
            println("Vessel Not Found")
    }

    fun addVesselData(vessel: VesselModel) : Boolean {                                  //Creates vessel if all VesselModel parameters are
                                                                                        //present
        println()
        print("Enter Vessel Name  : ")
        vessel.name = readLine()!!
        print("Enter Estimated Arrival Time : ")
        vessel.arrivalTime = readLine()!!
        print("Enter Vessel Draught :")
        vessel.draught = input.nextDouble()

        return vessel.name.isNotEmpty() && vessel.arrivalTime.isNotEmpty()
    }

    fun updateVesselData(vessel: VesselModel) : Boolean {                               //Updates vessel parameters; name, arrival time, draught

        var tempName: String?
        var tempArrivalTime: String?
        var tempDraught: Double?

        if (vessel != null) {
            print("Enter a new Name for [ " + vessel.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Arrival Time for [ " + vessel.arrivalTime + " ] : ")
            tempArrivalTime = readLine()!!
            print("Enter a new Draught for [ " + vessel.draught + " ] : ")
            tempDraught = readLine()!!.toDouble()

            if (!tempName.isNullOrEmpty() && !tempArrivalTime.isNullOrEmpty()) {
                vessel.name = tempName
                vessel.arrivalTime = tempArrivalTime
                vessel.draught = tempDraught

                return true
            }
        }
        return false
    }

    fun filteredByVesselName(vessel: VesselJSONStore){                                 //Filters Vessels by user input (NAME) and prints
        var name: String?
        print("Enter Vessel Name : ")
        name = readLine()!!
        println(vessel.filterByName(name))
    }


    fun getId() : Long {                                                                //Searches Vessels by corresponding ID number
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