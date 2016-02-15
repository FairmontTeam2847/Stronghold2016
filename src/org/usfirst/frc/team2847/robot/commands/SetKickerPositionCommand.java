package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetKickerPositionCommand extends Command {
	double angle;

	public SetKickerPositionCommand(double angle) {
		// Use requires() here to declare subsystem dependencies
		super("Shooter" + "angle" + angle);
		requires(Robot.shooter);
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.shooter.setKickAngle(angle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
