package org.usfirst.frc.team2847.robot.subsystems;

import org.usfirst.frc.team2847.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Talon windowMotor1;
	Talon windowMotor2;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Arm(){
    	windowMotor1 = new Talon(RobotMap.armwindowMotor1);
    	windowMotor2 = new Talon(RobotMap.armwindowMotor2);
    	  	
    }
    
    public void moveArm1(double speed){
    	windowMotor1.set(speed);
    }
    
    public void moveArm2(double speed){
    	windowMotor2.set(speed);
    }
}

