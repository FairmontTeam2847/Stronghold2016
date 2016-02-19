package org.usfirst.frc.team2847.robot;

import org.usfirst.frc.team2847.robot.commands.AnglerCommand;
import org.usfirst.frc.team2847.robot.commands.AnglerDownCommand;
import org.usfirst.frc.team2847.robot.commands.AnglerUpCommand;
import org.usfirst.frc.team2847.robot.commands.Arm2Command;
import org.usfirst.frc.team2847.robot.commands.ArmCommand;
import org.usfirst.frc.team2847.robot.commands.KickNShootCommandGroup;
import org.usfirst.frc.team2847.robot.commands.SetShooterSpeedCommand;
import org.usfirst.frc.team2847.robot.commands.VisionCommand;
import org.usfirst.frc.team2847.robot.triggers.POVhat;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	// JOYSTICK DECLARE
	Joystick leftStick = new Joystick(RobotMap.leftJoy), rightStick = new Joystick(RobotMap.rightJoy),
			fancyStick = new Joystick(RobotMap.fancyJoy);
	// BUTTON DECLARE
	Button shootButton = new JoystickButton(fancyStick, RobotMap.shootButton),
			pullButton = new JoystickButton(fancyStick, RobotMap.pullButton),
			kickBallButton = new JoystickButton(fancyStick, RobotMap.kickNShootButton),
			autoTargetButton = new JoystickButton(fancyStick, RobotMap.autoTargetButton),
			highAnglerButton = new JoystickButton(fancyStick, RobotMap.highAnglerButton),
			lowAnglerButton = new JoystickButton(fancyStick, RobotMap.lowAnglerButton),
			kickNShootButton = new JoystickButton(fancyStick, RobotMap.kickNShootButton),
			arm1UpButton = new JoystickButton(fancyStick, RobotMap.arm1UpButton),
			arm1DownButton = new JoystickButton(fancyStick, RobotMap.arm1DownButton),
			arm2UpButton = new JoystickButton(fancyStick, RobotMap.arm2UpButton),
			arm2DownButton = new JoystickButton(fancyStick, RobotMap.arm2DownButton);

	POVhat hat = new POVhat(fancyStick);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	public OI() {
		shootButton.whileHeld(new SetShooterSpeedCommand(RobotMap.shootSpeed));
		pullButton.whileHeld(new SetShooterSpeedCommand(RobotMap.pullSpeed));
		kickBallButton.whenPressed(new KickNShootCommandGroup());
		autoTargetButton.whenPressed(new VisionCommand(RobotMap.setpointValue));
		highAnglerButton.whenPressed(new AnglerUpCommand());
		lowAnglerButton.whenPressed(new AnglerDownCommand());
		arm1UpButton.whileHeld(new ArmCommand(RobotMap.armSpeed));
		arm1DownButton.whileHeld(new ArmCommand(-RobotMap.armSpeed));
		arm2UpButton.whileHeld(new Arm2Command(-RobotMap.armSpeed));
		arm2DownButton.whileHeld(new Arm2Command(RobotMap.armSpeed));

		hat.Up.whileActive(new AnglerCommand(RobotMap.anglerSpeed));
		hat.Down.whileActive(new AnglerCommand(RobotMap.anglerDownSpeed));

	}

	public void updateDash() {
		SmartDashboard.putData("Shooter", Robot.shooter);
		SmartDashboard.putData("Drivetrain", Robot.drivetrain);
		SmartDashboard.putData("Angler", Robot.angler);
		SmartDashboard.putNumber("Limit switch", Robot.angler.outOfRange());
		SmartDashboard.putNumber("POV", this.getPOV());
		// Robot.prefs.getDouble("driveP", RobotMap.kDriveP);
		// Robot.prefs.getDouble("driveI", RobotMap.kDriveI);
		// Robot.prefs.getDouble("driveD", RobotMap.kDriveD);
	}

	public double getLeftJoyY() {
		return leftStick.getY();
	}

	public double getLeftJoyX() {
		return leftStick.getX();
	}

	public double getRightJoyY() {
		return rightStick.getY();
	}

	public double getRightJoyX() {
		return rightStick.getX();
	}

	public double getFancyJoyX() {
		return fancyStick.getX();
	}

	public double getFancyJoyY() {
		return fancyStick.getY();
	}

	public double getFancyJoyTwist() {
		return fancyStick.getTwist();
	}

	public double getFancyJoyThrottle() {
		return fancyStick.getThrottle();
	}

	public double getPOV() {
		return fancyStick.getPOV();
	}

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

}
