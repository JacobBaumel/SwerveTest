package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.constants.JoystickConstants;
import frc.robot.subsystems.SwerveBase;

public class TankDrive extends CommandBase {

    private final SwerveBase base;

    public TankDrive(SwerveBase base) {
        this.base = base;
        addRequirements(base);
    }

    @Override
    public void initialize() {
        base.driveTank(0, 0);
    }

    @Override
    public void execute() {
        double left = RobotContainer.joystick.getRawAxis(JoystickConstants.LEFT_Y_AXIS);
        double right = RobotContainer.joystick.getRawAxis(JoystickConstants.RIGHT_Y_AXIS);

        base.driveTank(left / 4, right / 4);
    }
}
