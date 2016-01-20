package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;

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
	}

	Talon leftShooter = new Talon(RobotMap.leftShooterMotor), rightShooter = new Talon(RobotMap.rightShooterMotor);
	Servo kickServo = new Servo(RobotMap.kickServo);

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

	public boolean autoKick() {
		int i = 0;
		while (i < 20) {
			if (i < 4) {
				this.setKickAngle(0);
				return false;
			} else if (i >= 4 && i <= 18) {
				this.setKickAngle(110);
				return false;
			} else if (i > 18) {
				this.setKickAngle(0);
				return true;
			}
			i++;
			Timer.delay(0.05);
		}
		return true;

	}

	public double getShooterSpeed() {
		return leftShooter.get();
	}
}
