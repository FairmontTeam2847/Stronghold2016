package org.usfirst.frc.team2847.robot.commands;

import org.usfirst.frc.team2847.robot.Robot;
import org.usfirst.frc.team2847.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class RotCommand extends PIDCommand {

	public RotCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("RotCommand", .04, RobotMap.kDriveI, 0.2);
		requires(Robot.drivetrain);
		getPIDController().setPercentTolerance(6);
		getPIDController().setInputRange(0, RobotMap.camMaxX);
		getPIDController().setOutputRange(-0.5, 0.5);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(this);
		getPIDController().reset();
		getPIDController().enable();
		getPIDController().setSetpoint(0);
		Robot.drivetrain.findMaxArea();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.drivetrain.isContours()) {
			Robot.drivetrain.findMaxArea();
		} else {
			end();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// if (getPIDController().getError() < 2 &&
		// getPIDController().getError() > -2) {
		if (getPIDController().onTarget()) {
			System.out.println("rotdone");
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		getPIDController().disable();
		Robot.drivetrain.cleanupCrew();
		Robot.drivetrain.initDefaultCommand();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.drivetrain.offsetCalc();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.drivetrain.arcDrive(0, -output);
	}
}
