package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class JoystickDrive extends Command {

	public JoystickDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.table = NetworkTable.getTable("GRIP/myContoursReport");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Robot.drivetrain.manDrive(-Robot.oi.getLeftJoyY(),
		// -Robot.oi.getFancyJoyY());
		Robot.drivetrain.arcDrive(-Robot.oi.getFancyJoyY(), -Robot.oi.getFancyJoyTwist());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
