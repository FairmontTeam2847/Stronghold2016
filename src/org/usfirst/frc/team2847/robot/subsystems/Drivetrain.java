package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;
import org.usfirst.frc.team2847.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

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
}
