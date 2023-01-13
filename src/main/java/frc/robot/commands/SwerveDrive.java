package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.constants.JoystickConstants;
import frc.robot.subsystems.SwerveBase;

public class SwerveDrive extends CommandBase {

    private final SwerveBase base;

    public SwerveDrive(SwerveBase base) {
        this.base = base;
        addRequirements(base);
    }

    @Override
    public void initialize() {
        base.driveTank(0, 0);
    }

    @Override
    public void execute() {
        double xspeed = RobotContainer.joystick.getRawAxis(JoystickConstants.LEFT_X_AXIS);
        double yspeed = RobotContainer.joystick.getRawAxis(JoystickConstants.LEFT_Y_AXIS);
        double zrot = RobotContainer.joystick.getRawAxis(JoystickConstants.RIGHT_X_AXIS);

        xspeed = MathUtil.applyDeadband(xspeed, 0.02);
        yspeed = MathUtil.applyDeadband(yspeed, 0.02);
        zrot = MathUtil.applyDeadband(zrot, 0.02);

        base.driveSwerve(xspeed, yspeed, zrot);

    }
}
