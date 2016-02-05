package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;
import org.usfirst.frc.team2847.robot.commands.ShootCommand;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
		setDefaultCommand(new ShootCommand(0));
	}

	// Talon leftShooter;
	// Talon rightShooter;
	// Servo kickServo;

	Talon leftShooter = new Talon(RobotMap.leftShooterMotor);
	Talon rightShooter = new Talon(RobotMap.rightShooterMotor);
	Servo kickServo = new Servo(RobotMap.kickServo);

	// public Shooter() {
	// leftShooter = new Talon(RobotMap.leftShooterMotor);
	// rightShooter = new Talon(RobotMap.rightShooterMotor);
	// kickServo = new Servo(RobotMap.kickServo);
	// }

	public void spinShooters(double speed) {
		leftShooter.set(speed);
		rightShooter.set(speed);
	}

	public void setKickAngle(double angle) {
		kickServo.setAngle(angle);
	}

	public double getKickAngle() {
		return kickServo.getAngle();
	}

	public boolean doneKicking;

	Thread kickThread = new Thread() {
		public void run() {
			int i = 0;
			for (; i < 20; i++) {
				if (i == 1) {
					setKickAngle(RobotMap.kickDefaultAngle);
					doneKicking = false;
				} else if (i == 4) {
					setKickAngle(RobotMap.kickHitAngle);
					doneKicking = false;
				} else if (i == 15) {
					setKickAngle(RobotMap.kickDefaultAngle);
					doneKicking = true;
				}
				Timer.delay(0.05);

			}
			doneKicking = true;

			kickThread.interrupt();
		}
	};

	public void autoKick() {

		kickThread.run();

	}

	public double getShooterSpeed() {
		return leftShooter.get();
	}
}
