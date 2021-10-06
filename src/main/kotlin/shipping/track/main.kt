package shipping.track

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var name = ""
var arrivalTime = ""

fun main(args: Array<String>) {
    logger.info { "Launching VesselTracker App" }
    println("VesselTracker 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addVessel()
            2 -> updateVessel()
            3 -> listVessels()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Closing VesselTracker App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("MAIN MENU")
    println(" 1. Add Vessel")
    println(" 2. Update Vessel")
    println(" 3. List All Vessels")
    println("-1. Exit")
    println()
    print("Choose an option: ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addVessel(){
    println("Add Vessel")
    println()
    print("Enter vessel name: ")
    name = readLine()!!
    print("Enter vessel arrival time: ")
    arrivalTime = readLine()!!
    println("Your vessel $name with the arrival time of $arrivalTime as been logged")
}

fun updateVessel() {
    println("You Chose Update Vessel")
}

fun listVessels() {
    println("You Chose List All Vessels")
}