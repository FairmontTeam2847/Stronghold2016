package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;
import org.usfirst.frc.team2847.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AnglerUpCommand extends Command {

	public AnglerUpCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.angler);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.angler.moveAngler(RobotMap.anglerSpeed * .85);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.angler.outOfRange() == 1;
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
