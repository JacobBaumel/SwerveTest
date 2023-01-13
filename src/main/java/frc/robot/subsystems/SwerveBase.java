package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.SwerveDrive;
import frc.robot.commands.TankDrive;
import frc.robot.constants.SwerveConstants;

public class SwerveBase extends SubsystemBase {

    private final WPI_TalonFX[] driveMotors;
    private final WPI_TalonFX[] turnMotors;

    private final SwerveDriveKinematics kinematics;

    public SwerveBase() {
        driveMotors = new WPI_TalonFX[4];
        turnMotors = new WPI_TalonFX[4];

        for(int i = 0; i < 4; i++) {
            driveMotors[i] = new WPI_TalonFX(SwerveConstants.driveMotors[i]);
            turnMotors[i] = new WPI_TalonFX(SwerveConstants.turnMotors[i]);
            driveMotors[i].configFactoryDefault();
            turnMotors[i].configFactoryDefault();
            driveMotors[i].configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
            turnMotors[i].configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
            driveMotors[i].selectProfileSlot(0, 0);
            turnMotors[i].selectProfileSlot(0, 0);
            driveMotors[i].setNeutralMode(NeutralMode.Brake);
            turnMotors[i].setNeutralMode(NeutralMode.Brake);
            driveMotors[i].config_kP(0, SwerveConstants.drive_kP);
            driveMotors[i].config_kI(0, SwerveConstants.drive_kI);
            turnMotors[i].config_kP(0, SwerveConstants.turn_kP);
            driveMotors[i].config_kF(0, SwerveConstants.drive_kF);
            turnMotors[i].config_kF(0, SwerveConstants.turn_kF);
            turnMotors[i].config_kI(0, SwerveConstants.turn_kI);
            driveMotors[i].set(0);
            turnMotors[i].set(0);
        }

        kinematics = new SwerveDriveKinematics(
                SwerveConstants.flLoc, SwerveConstants.frLoc, SwerveConstants.brLoc, SwerveConstants.blLoc);

        setDefaultCommand(new SwerveDrive(this));
    }

    public void driveTank(double left, double right) {
        driveMotors[0].set(ControlMode.PercentOutput, left);
        driveMotors[3].set(ControlMode.PercentOutput, left);
        driveMotors[1].set(ControlMode.PercentOutput, right);
        driveMotors[2].set(ControlMode.PercentOutput, right);
    }

    @Override
    public void periodic() {
//        for(int i = 0; i < 4; i++) {
//            turnMotors[i].set(ControlMode.Velocity, 2048);
//            System.out.print("Motor " + i + ": " + turnMotors[i].getSelectedSensorVelocity() + "  ");
//        }
//        System.out.println();
    }

    public void driveSwerve(double xspeed, double yspeed, double zrot) {
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(new ChassisSpeeds(xspeed, yspeed, zrot));
        SwerveDriveKinematics.desaturateWheelSpeeds(states, 3);
        for(int i = 0; i < 4; i++) {
            states[i] = SwerveModuleState.optimize(states[i],
                    Rotation2d.fromDegrees(turnMotors[i].getSelectedSensorPosition() * SwerveConstants.MTTD));
            System.out.println("Moduel " + i + " speed: " +
                    (states[i].speedMetersPerSecond / SwerveConstants.MTP100MSTMPS) + ", Angle: " +
                    (states[i].angle.getDegrees() / SwerveConstants.MTTD));
//            driveMotors[i].set(ControlMode.Velocity, 2048);
//            turnMotors[i].set(ControlMode.PercentOutput, yspeed / 4);
            driveMotors[i].set(ControlMode.Velocity, (states[i].speedMetersPerSecond / SwerveConstants.MTP100MSTMPS));
            turnMotors[i].set(ControlMode.Position, states[i].angle.getDegrees() / SwerveConstants.MTTD);
        }
    }
}
