package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;
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

		// Start spinning up shooters and kick while they spin
		addSequential(new SetShooterSpeedCommand(RobotMap.shootSpeed), 1.5);
		addSequential(new SetKickerPositionCommand(RobotMap.kickHitAngle));
		addSequential(new SetShooterSpeedCommand(RobotMap.shootSpeed), 1);

		addSequential(new WaitCommand(1));

		// Stop shooter and return kicker to default position

		addSequential(new SetKickerPositionCommand(RobotMap.kickDefaultAngle));
		addParallel(new SetShooterSpeedCommand(0), 0.5);

		if (Robot.drivetrain.isContours() && Robot.drivetrain.timeSinceInit() > 2)
			Robot.drivetrain.cancelCommand();
	}
}
