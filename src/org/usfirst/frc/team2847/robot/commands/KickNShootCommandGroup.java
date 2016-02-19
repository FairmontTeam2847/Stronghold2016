package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class KickNShootCommandGroup extends CommandGroup {

	public KickNShootCommandGroup() {
		super("KickNShootCommandGroup " + RobotMap.shootSpeed);

		// to get into default position
		addSequential(new SetKickerPositionCommand(RobotMap.kickDefaultAngle));
		addSequential(new AnglerUpCommand());

		// Start spinning up shooters and give them 2 seconds to reach speed
		addSequential(new SetShooterSpeedCommand(RobotMap.shootSpeed), 1.5);
		addSequential(new SetKickerPositionCommand(RobotMap.kickHitAngle));
		addSequential(new SetShooterSpeedCommand(RobotMap.shootSpeed), 1);

		// Move kicker into kick position and give .5 second for boulder
		// to pass through

		addSequential(new WaitCommand(1));

		// Stop shooter and return kicker to default position

		addSequential(new SetKickerPositionCommand(RobotMap.kickDefaultAngle));
		addParallel(new SetShooterSpeedCommand(0), 0.5);
	}
}
