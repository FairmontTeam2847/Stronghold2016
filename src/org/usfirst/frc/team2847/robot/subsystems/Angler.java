package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Angler extends Subsystem {

	// Initialize your subsystem here
	// Talon leftWindowMotor;
	Talon rightWindowMotor;

	DigitalInput lowLimit, highLimit;

	public Angler() {
		// leftWindowMotor = new Talon(RobotMap.anglerWindowLeftMotor);
		rightWindowMotor = new Talon(RobotMap.anglerWindowRightMotor);

		lowLimit = new DigitalInput(RobotMap.lowLimit);
		highLimit = new DigitalInput(RobotMap.highLimit);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void moveAngler(double speed) {
		// leftWindowMotor.set(-speed);
		rightWindowMotor.set(speed);
	}

	public boolean inRange() {
		if (lowLimit.get() || highLimit.get()) {
			return false;
		} else {
			return true;
		}
	}

	public int outOfRange() {
		if (highLimit.get()) {
			return 1;
		} else if (lowLimit.get()) {
			return -1;
		} else {
			return 0;
		}
	}

}
