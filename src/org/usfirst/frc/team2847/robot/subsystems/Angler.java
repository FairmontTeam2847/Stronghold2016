package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Angler extends PIDSubsystem {

	// Initialize your subsystem here
	Talon windowMotor;
	AnalogGyro anglerGyro;

	public Angler() {
		super("Drivetrain", RobotMap.kAnglerP, RobotMap.kAnglerI, RobotMap.kAnglerD);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		setPercentTolerance(5.0);
		windowMotor = new Talon(RobotMap.anglerWindowMotor);
		anglerGyro = new AnalogGyro(RobotMap.anglerGyro);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void moveAngler(double speed) {
		windowMotor.set(speed);
	}

	public boolean inRange() {
		if (anglerGyro.getAngle() > RobotMap.anglerSetpointHigh || anglerGyro.getAngle() < RobotMap.anglerSetpointLow) {
			return false;
		} else {
			return true;
		}
	}

	public int outOfRange() {
		if (anglerGyro.getAngle() > RobotMap.anglerSetpointHigh) {
			return 1;
		} else if (anglerGyro.getAngle() < RobotMap.anglerSetpointLow) {
			return -1;
		} else {
			return 0;
		}
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return anglerGyro.getAngle();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		windowMotor.set(output);
	}
}
