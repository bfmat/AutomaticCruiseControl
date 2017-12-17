package cruisecontrol

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.Relay
import java.io.File

// The channel number of the accelerator relay
private const val ACCELERATOR_RELAY_CHANNEL = 1

// The path to the file containing the data from the LIDAR sensor
private const val LIDAR_DATA_FILE_PATH = "/home/lvuser/lidar.dat"

// The main robot class
class Robot : IterativeRobot() {

    // The relay that controls the accelerator
    private lateinit var acceleratorRelay: Relay

    // The file containing the data that should be written by the software managing the LIDAR sensor
    private val lidarDataFile = File(LIDAR_DATA_FILE_PATH)

    // Called when the robot class is initialized
    override fun robotInit() {
        // Create the accelerator relay with the predefined port number
        acceleratorRelay = Relay(ACCELERATOR_RELAY_CHANNEL)
    }

    // Called periodically during autonomous mode
    // The car should always be in autonomous mode when enabled
    override fun autonomousPeriodic() {
        // Read the data from the LIDAR data file and convert it to a relay state representing
        // whether the relay should be on or off
        val relayState = lidarDataFile.readText().toBoolean()
        // Set the relay's state accordingly using relay state values
        acceleratorRelay.set(if (relayState) Relay.Value.kOn else Relay.Value.kOff)
    }
}