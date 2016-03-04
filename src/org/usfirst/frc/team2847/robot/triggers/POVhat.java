package org.usfirst.frc.team2847.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Allows the POV hat switch on the joystick to be represented like a group of 8
 * buttons. Attach commands as easily as with buttons.
 * 
 * @author Dustin Maki, modified for 2016 control system by Lucas Jed
 */
public class POVhat // outer class
{
	Joystick stick;
	int angle;
	public POVtrigger Right;
	public POVtrigger UpRight;
	public POVtrigger Up;
	public POVtrigger UpLeft;
	public POVtrigger Left;
	public POVtrigger DownLeft;
	public POVtrigger Down;
	public POVtrigger DownRight;

	public class POVtrigger extends Trigger // inner class
	{
		private final int angle;
		private boolean exclusive = false; // if set true(default), trigger is
											// active only when it exactly
											// matches position of hat switch.
											// if set false, trigger is active
											// in any position that shares
											// trigger name.
		// i.e. If Up is set not exclusive, Command associated with Up will
		// trigger if hat switch is UpLeft, UpRight, or Up.
		private static final int kRight = 90;
		private static final int kUpRight = 45;
		private static final int kUp = 0;
		private static final int kUpLeft = 315;
		private static final int kLeft = 270;
		private static final int kDownLeft = 225;
		private static final int kDown = 180;
		private static final int kDownRight = 135;

		protected POVtrigger(int angle)// inner class constructor
		{
			this.angle = angle;
		}

		public void setExclusive(boolean exclusive) // only useful on up, down,
													// left, and right
		{
			this.exclusive = exclusive;
		}

		public boolean get() // mandatory override from Trigger class
		{
			if (exclusive)
				return (stick.getPOV() == this.angle);
			else
				return (((stick.getPOV() + 45) == this.angle || (stick.getPOV() - 45) == this.angle
						|| (stick.getPOV() + 315) == this.angle) || stick.getPOV() == this.angle);
		}
	}
	// begin outer class constructors
	// public POVhat()
	// {
	// this(Robot.oi.driverJoystick, 5, 6);
	// }

	public POVhat(Joystick stick) {
		this.stick = stick;
		this.Right = new POVtrigger(POVtrigger.kRight);
		this.UpRight = new POVtrigger(POVtrigger.kUpRight);
		this.Up = new POVtrigger(POVtrigger.kUp);
		this.UpLeft = new POVtrigger(POVtrigger.kUpLeft);
		this.Left = new POVtrigger(POVtrigger.kLeft);
		this.DownLeft = new POVtrigger(POVtrigger.kDownLeft);
		this.Down = new POVtrigger(POVtrigger.kDown);
		this.DownRight = new POVtrigger(POVtrigger.kDownRight);
	}
	// end outer class constructors
}
