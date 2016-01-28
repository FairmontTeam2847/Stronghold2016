package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AnglerCommand extends Command {

	double speed;

	public AnglerCommand(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.angler);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.angler.outOfRange() == -1 && speed < 0) {
			Robot.angler.moveAngler(-speed);
		} else if (Robot.angler.outOfRange() == 1 && speed > 0) {
			Robot.angler.moveAngler(-speed);
		} else {
			Robot.angler.moveAngler(speed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.angler.moveAngler(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
