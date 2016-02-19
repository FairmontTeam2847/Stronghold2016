package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionCommand extends Command {

	double setpoint;

	public VisionCommand(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(this);
		Robot.drivetrain.getPIDController().reset();
		// System.out.println("THROTTLE" + Robot.oi.getFancyJoyThrottle());
		// Robot.drivetrain.getPIDController().setPID(1 *
		// Robot.oi.getFancyJoyThrottle(), RobotMap.kDriveI,
		// RobotMap.kDriveD);
		Robot.drivetrain.getPIDController().enable();
		Robot.drivetrain.findMaxArea();
		Robot.drivetrain.getPIDController().setSetpoint(setpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.drivetrain.isContours()) {
			Robot.drivetrain.useCenter();
		} else {
			end();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drivetrain.getPIDController().onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.getPIDController().disable();
		Robot.drivetrain.cleanupCrew();
		Robot.drivetrain.initDefaultCommand();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
