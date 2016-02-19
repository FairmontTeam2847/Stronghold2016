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
	public static int frontLeftDrive = 1;
	// public static int frontRightDrive = ;
	// public static int rearLeftDrive = ;
	public static int rearRightDrive = 4;
	public static int leftShooterMotor = 6;
	public static int rightShooterMotor = 7;
	public static int anglerWindowLeftMotor = 8;
	public static int anglerWindowRightMotor = 9;
	public static int armwindowMotor1 = 5;
	public static int armwindowMotor2 = 0;

	// Actuators
	public static int kickServo = 2;

	// Joysticks
	public static int leftJoy = 0;
	public static int rightJoy = 1;
	public static int fancyJoy = 2;

	// Buttons

	public static int shootButton = 5;
	public static int pullButton = 2;
	public static int autoTargetButton = 7;
	public static int highAnglerButton = 4;
	public static int lowAnglerButton = 3;
	public static int kickNShootButton = 1;
	public static int arm1UpButton = 10;
	public static int arm1DownButton = 9;
	public static int arm2UpButton = 12;
	public static int arm2DownButton = 11;

	// Sensors

	public static int anglerGyro = 0;
	public static int lowLimit = 0;
	public static int highLimit = 1;

	// PID TUNE
	public static double kDriveP = 2;
	public static double kDriveI = 0;
	public static double kDriveD = 0;
	public static double kAnglerP = 0.9;
	public static double kAnglerI = 0.1;
	public static double kAnglerD = 0.1;
	public static int setpointValue = 70;
	public static double anglerSetpointHigh = 40;
	public static double anglerSetpointFlat = 2;
	public static double anglerSetpointLow = 0;
	public static int kickHitAngle = 160;
	public static int kickDefaultAngle = 40;

	// SPEEDS
	public static double shootSpeed = 1;
	public static double pullSpeed = -0.6;
	public static double anglerSpeed = 0.8;
	public static double anglerDownSpeed = -0.5;
	public static double armSpeed = 1;
}
