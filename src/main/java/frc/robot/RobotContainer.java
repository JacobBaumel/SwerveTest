package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.JoystickConstants;
import frc.robot.subsystems.SwerveBase;

public class RobotContainer {

    public static final Joystick joystick = new Joystick(JoystickConstants.JOYSTICK_PORT);
    public static final SwerveBase base = new SwerveBase();
    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {

    }



    public Command getAutonomousCommand() {
        return null;
    }
}
