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
	double[] areas = {};
	double maxArea = 0;

	public Drivetrain() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		super("Drivetrain", RobotMap.kP, RobotMap.kI, RobotMap.kD);
		setAbsoluteTolerance(500);
		Robot.table.putNumberArray("area", areas);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new JoystickDrive());
	}

	// create our drivetrain object
	RobotDrive driveTrain = new RobotDrive(RobotMap.frontLeftDrive, RobotMap.rearLeftDrive, RobotMap.frontRightDrive,
			RobotMap.rearRightDrive);

	// make the method for our drivetrain
	public void tankDrive(double leftValue, double rightValue) {
		driveTrain.tankDrive(leftValue, rightValue);
	}

	/*
	 * starts a counter and for each item in the number array compares it to the
	 * max value from before in the end of the loop we should have max = the
	 * biggest value in our array we do this to find out which contour is our
	 * target this should filter out any minor interference in the camera
	 */
	public void findMaxArea() {
		Robot.table.putNumberArray("area", areas);
		for (int counter = 1; counter < areas.length; counter++) {
			if (areas[counter] > maxArea) {
				maxArea = areas[counter];
			}
		}
		System.out.println(maxArea);
	}

	public void updateArea() {
		Robot.table.putNumberArray("area", areas);
		findMaxArea();
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return maxArea;
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		this.tankDrive(output, output);
	}
}
