package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Auto.AutoSeries;
import frc.team5115.Commands.IntakeBalls;
import frc.team5115.Subsystems.*;

import static frc.team5115.Constants.*;

public class RobotContainer {

    // The robot's subsystems and commands are defined here...
    //Subsystems
    private final Drivetrain drivetrain = new Drivetrain(this);
    public final Locationator locationator = new Locationator(drivetrain, startingConfiguration, startingAngle);
    public final Limelight limelight = new Limelight();
    public final Shooter shooter = new Shooter();
    public final Intake intake = new Intake();
    public static Joystick joy = new Joystick(0);
    //commands
    private final AutoSeries autoSeries = new AutoSeries(drivetrain, locationator, shooter, limelight);


    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        //sets the navx to work.
    }

    private Button intake_Button = new JoystickButton(joy, INTAKE_BUTTON_ID);
    private Button shooter_Button = new JoystickButton(joy, SHOOTER_BUTTON_ID);

    private void configureButtonBindings() {
        intake_Button.whenPressed(new IntakeBalls(intake));
        shooter_Button.whenPressed(new InstantCommand(shooter::shoot));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoSeries;
    }

    public void startTeleop() {
        //bind the wheels.
        System.out.println("Starting teleop");
        new RunCommand(() -> {

            drivetrain.drive(
                    joy.getRawAxis(X_AXIS_ID),
                    -joy.getRawAxis(Y_AXIS_ID), //note: negative because pushing forward is a negative value on the joystick.
                    0.3);//joy.getRawAxis(THROTTLE_AXIS_ID));
        }).schedule();
    }
}