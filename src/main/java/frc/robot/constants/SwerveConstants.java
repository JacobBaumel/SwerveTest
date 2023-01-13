package frc.robot.constants;

import edu.wpi.first.math.geometry.Translation2d;

public class SwerveConstants {

    public static final int[] driveMotors = new int[] {2, 4, 6, 8};
    public static final int[] turnMotors = new int[] {1, 3, 5, 7};

    public static final double turn_kP = 0.025;
    public static final double turn_kF = 0.1;
    public static final double turn_kI = 0.001;
    public static final double drive_kP = 0.025;
    public static final double drive_kI = 0.001;
    public static final double drive_kF = 0.1;

    public static final Translation2d flLoc = new Translation2d(0.381, 0.381);
    public static final Translation2d frLoc = new Translation2d(0.381, -0.381);
    public static final Translation2d brLoc = new Translation2d(-0.381, -0.381);
    public static final Translation2d blLoc = new Translation2d(-0.381, 0.381);

    public static final double WHEEL_DIA = 4;
    /*
     * 1 ds = 1 decisecond or 100ms
     *
     * The magic number is derived from:
     *
     * x ticks * 10ds * 1 revolution of output shaft * 14 revolutions of driven gear of swerve module * circumfrence of the wheel in inches * 1m
     * -----------------------------------------------------------------------------------------------------------------------------------------
     * 1ds * 1s * 2048 motor ticks * 45 revolutions of drive gear * 39.3071 in.
     *
     * x ticks/ds -> y m/s
     * MTP100MSTMPS = Motor ticks per 100ms to meters per second
     * */
    public static final double MTP100MSTMPS = (10.0 * 14.0 * Math.PI * WHEEL_DIA) / (2048.0 * 45.0 * 39.3701);

    /*
     *
     * The magic number is derived from:
     *
     * x ticks * 1 revolution * 360 degrees
     * -------------------------------------
     * 2048 ticks * 1 revolution
     *
     * x ticks -> degrees
     *
     * MTTD = Motor ticks to degrees
     * */
    public static final double MTTD = 360.0 / 2048.0;
}
