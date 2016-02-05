package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.Robot;
import org.usfirst.frc.team2847.robot.RobotMap;
import org.usfirst.frc.team2847.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Drivetrain extends PIDSubsystem {

	// Initialize your subsystem here
	double[] greenAreasArray = {};
	double[] greenXArray = {};
	double[] greenYArray = {};

	double maxArea = 0;
	double greenX = 0;
	double greenY = 0;
	double offsetX;

	int arrayNum = 0;

	// create our drivetrain object
	RobotDrive driveTrain = new RobotDrive(RobotMap.frontLeftDrive, RobotMap.rearLeftDrive, RobotMap.frontRightDrive,
			RobotMap.rearRightDrive);

	public Drivetrain() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		super("Drivetrain", RobotMap.kDriveP, RobotMap.kDriveI, RobotMap.kDriveD);
		setPercentTolerance(5.0);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new JoystickDrive());
	}

	// make the method for our drivetrain
	public void manDrive(double leftValue, double rightValue) {
		driveTrain.tankDrive(leftValue, rightValue);
	}

	/*
	 * starts a counter and for each item in the number array compares it to the
	 * max value from before in the end of the loop we should have max = the
	 * biggest value in our array we do this to find out which contour is our
	 * target this should filter out any minor interference in the camera
	 */
	public void findMaxArea() {
		Robot.table.getNumberArray("area", greenAreasArray);
		for (int counter = 0; counter < greenAreasArray.length; counter++) {
			if (greenAreasArray[counter] > maxArea) {
				maxArea = greenAreasArray[counter];
				arrayNum = counter;
			}
		}
		System.out.println(maxArea);
	}

	public void updateArea() {
		Robot.table.getNumberArray("area", greenAreasArray);
		findMaxArea();
	}

	public void useCenter() {
		this.updateArea();
		Robot.table.getNumberArray("centerX", greenXArray);
		Robot.table.putNumberArray("centerY", greenYArray);
		greenX = greenXArray[arrayNum];
		greenY = greenYArray[arrayNum];
	}

	public double offsetCalc() {
		this.useCenter();
		offsetX = 320 - greenX;
		return offsetX;
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
		if (offsetCalc() > 0) {
			this.manDrive(output * (greenX / 320), output);
		} else if (offsetCalc() < 0) {
			this.manDrive(output, output * (greenX / 320));
		} else {
			this.manDrive(output, output);
		}
	}
}
