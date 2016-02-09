package org.usfirst.frc.team2847.robot;

import org.usfirst.frc.team2847.robot.commands.AnglerCommand;
import org.usfirst.frc.team2847.robot.commands.AutoAnglerCommand;
import org.usfirst.frc.team2847.robot.commands.KickNShootCommandGroup;
import org.usfirst.frc.team2847.robot.commands.SetShooterSpeedCommand;
import org.usfirst.frc.team2847.robot.commands.VisionCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
			kickBallButton = new JoystickButton(fancyStick, RobotMap.kickBallButton),
			anglerUpButton = new JoystickButton(fancyStick, RobotMap.anglerUpButton),
			anglerDownButton = new JoystickButton(fancyStick, RobotMap.anglerDownButton),
			autoTargetButton = new JoystickButton(fancyStick, RobotMap.autoTargetButton),
			highAnglerButton = new JoystickButton(fancyStick, RobotMap.highAnglerButton),
			flatAnglerButton = new JoystickButton(fancyStick, RobotMap.flatAnglerButton),
			lowAnglerButton = new JoystickButton(fancyStick, RobotMap.lowAnglerButton),
			kickNShootButton = new JoystickButton(fancyStick, RobotMap.kickNShootButton);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	public OI() {
		shootButton.whileHeld(new SetShooterSpeedCommand(RobotMap.shootSpeed));
		pullButton.whileHeld(new SetShooterSpeedCommand(-RobotMap.shootSpeed));
		kickBallButton.whenPressed(new KickNShootCommandGroup());
		autoTargetButton.whenPressed(new VisionCommand(RobotMap.setpointValue));
		highAnglerButton.whenPressed(new AutoAnglerCommand(RobotMap.anglerSetpointHigh));
		flatAnglerButton.whenPressed(new AutoAnglerCommand(RobotMap.anglerSetpointFlat));
		lowAnglerButton.whenPressed(new AutoAnglerCommand(RobotMap.anglerSetpointLow));
		anglerUpButton.whenPressed(new AnglerCommand(RobotMap.anglerSpeed));
		anglerDownButton.whenPressed(new AnglerCommand(-RobotMap.anglerSpeed));
		kickNShootButton.whenPressed(new KickNShootCommandGroup());
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
