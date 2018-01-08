package cruisecontrol

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.Relay
import java.io.File

// The channel number of the accelerator relay
private const val ACCELERATOR_RELAY_CHANNEL = 3

// The path to the file containing the data from the LIDAR sensor
private const val LIDAR_DATA_FILE_PATH = "/home/lvuser/lidar.dat"

// The main robot class
class Robot : IterativeRobot() {

    // The relay that controls the accelerator
    private lateinit var acceleratorRelay: Relay

    // Called when the robot class is initialized
    override fun robotInit() {
        // Create the accelerator relay with the predefined port number
        acceleratorRelay = Relay(ACCELERATOR_RELAY_CHANNEL)
    }

    // Called periodically during autonomous mode
    // The car should always be in autonomous mode when enabled
    override fun autonomousPeriodic() {
        // Create the file containing the data that should be written by the LIDAR software
        val lidarDataFile = File(LIDAR_DATA_FILE_PATH)
        // If the LIDAR data file exists
        if (lidarDataFile.exists()) {
            // Read the data from the LIDAR data file, trim whitespace, and convert it to lowercase
            val lidarData = lidarDataFile.readText().trim().toLowerCase()
            // Convert it to a Boolean representing whether the relay should be on or off
            val relayState = lidarData.toBoolean()
            // Set the relay's state accordingly using a relay-specific data type
            acceleratorRelay.set(if (relayState) Relay.Value.kOn else Relay.Value.kOff)
        }
        // Otherwise, print an error message
        else {
            println("Error: LIDAR data file does not exist")
        }
    }
}