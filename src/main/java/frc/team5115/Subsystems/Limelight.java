package frc.team5115.Subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import static java.lang.Math.tan;
import static java.lang.Math.toRadians;
import static frc.team5115.Robot.RobotContainer.*;

public class Limelight {

    NetworkTableEntry pipeline = NetworkTableInstance.getDefault().getEntry("pipeline");

    int currentPipeline;

    public double getXAngle() {
        return 0;
    }

    public double getYAngle() {
        return 0;
    }

    public boolean hasTarget() {
        return false;
    }

    public void setPipeline(int pipe) {
        if (pipe != currentPipeline) { //if the new value is different than the past values, change it up.
            pipeline.setNumber(pipe);
            currentPipeline = pipe;
            System.out.println("Changed Pipeline to " + pipe);
        }
    }

    public double calculateDistanceFromBase() {
        return (HIGH_GOAL_HEIGHT - CAMERA_HEIGHT) / tan(toRadians(getYAngle() + CAMERA_ANGLE)); //
    }
}