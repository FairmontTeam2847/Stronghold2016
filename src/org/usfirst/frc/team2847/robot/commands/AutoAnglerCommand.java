package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAnglerCommand extends Command {

	double setpoint;

	public AutoAnglerCommand(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.angler);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.angler.getPIDController().reset();
		Robot.angler.getPIDController().enable();
		Robot.angler.setSetpoint(setpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.angler.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.angler.getPIDController().disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
