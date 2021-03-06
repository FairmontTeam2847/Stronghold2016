
package org.usfirst.frc.team2847.robot;

import org.usfirst.frc.team2847.robot.commands.autoDrive;
import org.usfirst.frc.team2847.robot.subsystems.Angler;
import org.usfirst.frc.team2847.robot.subsystems.Arm;
import org.usfirst.frc.team2847.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2847.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	public static Shooter shooter;
	public static Angler angler;
	public static Drivetrain drivetrain;
	public static Arm arm;

	Command autonomousCommand;
	SendableChooser chooser;

	public static Preferences prefs;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		shooter = new Shooter();
		angler = new Angler();
		arm = new Arm();

		NetworkTable.setServerMode();
		drivetrain = new Drivetrain();

		oi = new OI();

		chooser = new SendableChooser();
		chooser.addDefault("My Auto", new autoDrive(2));
		chooser.addObject("3 second Auto", new autoDrive(3));

		SmartDashboard.putData("Autonomous Chooser", chooser);
		// vision area

		// try {
		// new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch (autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		// for (double area :
		// drivetrain.table.getNumberArray("GRIP/myContoursReport", new
		// double[0])) {
		// System.out.println("Got contour with area=" + area);
		// }
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Robot.oi.updateDash();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
