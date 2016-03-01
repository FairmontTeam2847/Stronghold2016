package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;
import org.usfirst.frc.team2847.robot.commands.SetShooterSpeedCommand;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new SetShooterSpeedCommand(0));
	}

	// Talon leftShooter;
	// Talon rightShooter;
	// Servo kickServo;

	Talon leftShooter = new Talon(RobotMap.leftShooterMotor);
	// Talon rightShooter = new Talon(RobotMap.rightShooterMotor);
	Servo kickServo = new Servo(RobotMap.kickServo);

	// public Shooter() {
	// leftShooter = new Talon(RobotMap.leftShooterMotor);
	// rightShooter = new Talon(RobotMap.rightShooterMotor);
	// kickServo = new Servo(RobotMap.kickServo);
	// }

	public void spinShooters(double speed) {
		leftShooter.set(-speed);
		// rightShooter.set(speed);
		if (speed < 0) {
			setKickAngle(RobotMap.kickDefaultAngle);
		}
	}

	public void setKickAngle(double angle) {
		kickServo.setAngle(angle);
	}

	public double getKickAngle() {
		return kickServo.getAngle();
	}

	public double getShooterSpeed() {
		return leftShooter.get();
	}
}
