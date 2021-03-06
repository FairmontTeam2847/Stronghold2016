package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeedCommand extends Command {
	double speed;

	public SetShooterSpeedCommand(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("Shooter" + speed);
		requires(Robot.shooter);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.shooter.spinShooters(speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.spinShooters(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
