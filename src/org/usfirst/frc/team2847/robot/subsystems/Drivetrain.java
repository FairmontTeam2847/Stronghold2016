package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;
import org.usfirst.frc.team2847.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends PIDSubsystem {

	// Initialize your subsystem here

	double[] greenAreasArray = {};
	double[] greenXArray = {};
	double[] greenYArray = {};
	double[] defaultValue = new double[0];

	double maxArea = 0;
	double greenX = 0;
	double greenY = 0;
	double offsetX;

	int arrayNum = 0;

	// create our drivetrain object
	RobotDrive myDriveTrain = new RobotDrive(RobotMap.leftDriveMotors, RobotMap.rightDriveMotors);

	public NetworkTable table = NetworkTable.getTable("GRIP/myContoursReport");

	public Drivetrain() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		super("Drivetrain", RobotMap.kDriveP, RobotMap.kDriveI, RobotMap.kDriveD);
		setInputRange(0, RobotMap.camMaxY);
		setOutputRange(-0.6, 0.6);
		setAbsoluteTolerance(5.0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new JoystickDrive());
	}

	// make the method for our drivetrain
	public void manDrive(double leftValue, double rightValue) {
		myDriveTrain.tankDrive(leftValue, rightValue);
	}

	public void arcDrive(double mag, double rot) {
		myDriveTrain.arcadeDrive(mag, rot);
	}

	/*
	 * starts a counter and for each item in the number array compares it to the
	 * max value from before in the end of the loop we should have max = the
	 * biggest value in our array we do this to find out which contour is our
	 * target this should filter out any minor interference in the camera
	 */

	public boolean isContours() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
		greenAreasArray = table.getNumberArray("area", defaultValue);
		if (greenAreasArray.length > 0) {
			return true;
		} else {
			System.out.println("no array");
			return false;
		}
	}

	public void findMaxArea() {
		if (isContours()) {
			for (int counter = 0; counter < greenAreasArray.length; counter++) {
				if (greenAreasArray[counter] > maxArea) {
					maxArea = greenAreasArray[counter];
					arrayNum = counter;
				}
			}
			// System.out.print(maxArea);
		}
	}

	public void useCenter() {
		this.findMaxArea();
		if (isContours()) {
			greenXArray = table.getNumberArray("centerX", defaultValue);
			greenYArray = table.getNumberArray("centerY", defaultValue);
			greenX = greenXArray[arrayNum];
			greenY = greenYArray[arrayNum];
			SmartDashboard.putNumber("greenY", greenY);
		}
	}

	public double offsetCalc() {
		this.useCenter();
		offsetX = (RobotMap.camMaxX / 2) - greenX;
		return offsetX;
	}

	public double timeSinceInit() {
		return getCurrentCommand().timeSinceInitialized();
	}

	public void cancelCommand() {
		getCurrentCommand().cancel();
	}

	public void cleanupCrew() {
		NetworkTable.flush();
		this.manDrive(0, 0);
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return greenY;
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		double go;
		go = (-output);
		// if (offsetCalc() < 0) {
		// this.manDrive(go * (greenX / 320), go);
		// } else if (offsetCalc() > 0) {
		// this.manDrive(go, go * (greenX / 320));
		// } else {
		// this.manDrive(go, go);
		// }
		this.arcDrive(go, -(greenX / (RobotMap.camMaxX / 2)) / 4);
		SmartDashboard.putNumber("error", getPIDController().getError());
		// if (offsetCalc() > 0) {
		// this.arcDrive(go, -(greenX / 320) / 8);
		// } else if (offsetCalc() < 0) {
		// this.manDrive(go, (greenX / 320) / 8);
		// } else {
		// this.manDrive(go, 0);
		// }
	}
}
