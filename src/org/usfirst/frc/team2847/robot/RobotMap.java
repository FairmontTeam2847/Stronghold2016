package org.usfirst.frc.team2847.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// Motors
	public static int frontLeftDrive = 0;
	public static int frontRightDrive = 1;
	public static int rearLeftDrive = 2;
	public static int rearRightDrive = 3;
	public static int leftShooterMotor = 4;
	public static int rightShooterMotor = 5;
	public static int anglerWindowMotor = 6;

	// Actuators
	public static int kickServo = 1;

	// Joysticks
	public static int leftJoy = 1;
	public static int rightJoy = 2;
	public static int fancyJoy = 3;

	// Buttons

	public static int shootButton = 1;
	public static int pullButton = 2;
	public static int anglerDownButton = 3;
	public static int anglerUpButton = 4;
	public static int kickBallButton = 5;
	public static int autoTargetButton = 6;
	public static int highAnglerButton = 7;
	public static int flatAnglerButton = 8;
	public static int lowAnglerButton = 9;

	// Sensors

	public static int anglerGyro = 0;

	// PID TUNE
	public static double kDriveP = 0.5;
	public static double kDriveI = 0;
	public static double kDriveD = 0.33;
	public static double kAnglerP = 0.5;
	public static double kAnglerI = 0;
	public static double kAnglerD = 0.33;
	public static int setpointValue = 300;
	public static double anglerSetpointHigh = 25;
	public static double anglerSetpointFlat = 0;
	public static double anglerSetpointLow = -12;
	public static int kickHitAngle = 110;
	public static int kickDefaultAngle = 0;

	// SPEEDS
	public static double shootSpeed = 1;
	public static double anglerSpeed = 1;
}
