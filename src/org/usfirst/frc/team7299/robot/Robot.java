/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7299.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends IterativeRobot {
	
	// Compressor compressor = new Compressor();
	
	Joystick joystick = new Joystick(0);
	
	// DoubleSolenoid solenoid1 = new DoubleSolenoid(1,2);
	// DoubleSolenoid solenoid2 = new DoubleSolenoid(3,4);
	
	Spark motorLF = new Spark(0);
	Spark motorLB = new Spark(1);
	Spark motorRF = new Spark(2);
	Spark motorRB = new Spark(3);
	
	double speedL = 0;
	double speedR = 0;
	double speedComp = 0;
	
	boolean slow = false;
	
	final double accel = 1;
	
	boolean solenoidOn = false;

	@Override // Before anything
	public void robotInit() {
		System.out.println("INITIALIZED ROBOT!!");
		/*
		 * ### SOLENOID CODE ###
		 * 
		
		compressor.setClosedLoopControl(true);
		solenoid1.set(DoubleSolenoid.Value.kReverse);
		solenoid2.set(DoubleSolenoid.Value.kReverse);
		 */
	}

	@Override // Before autonomous control
	public void autonomousInit() {}

	@Override // During autonomous control
	public void autonomousPeriodic() {}
	
	@Override // Before teleop control
	public void teleopInit() {
		System.out.println("INITIALIZED TELEOP!!");
	} 
	
	@Override // During operator control
	public void teleopPeriodic() {
		/*
		 *   ### SOLENOID CODE ###
		 * 

		if(joystick.getRawButtonReleased(1)) {
			if(solenoidOn){
				solenoid1.set(DoubleSolenoid.Value.kReverse);
				solenoid2.set(DoubleSolenoid.Value.kReverse);
			} else {
				solenoid1.set(DoubleSolenoid.Value.kForward);
				solenoid2.set(DoubleSolenoid.Value.kForward);
			}
			solenoidOn = !solenoidOn;
		}

		if(joystick.getRawButtonReleased(2)) {
			if(compressor.enabled()) {
				compressor.stop();
			} else {
				compressor.start();
			}
		}
		*/
		
		double x = joystick.getX();
		double y = joystick.getY();
		
		double R = (y - x) / Math.sqrt(2);
		double L = (y + x) / Math.sqrt(2);
		
		if(joystick.getRawButtonReleased(4)) {
			slow = !slow;
		}
		double percent = (slow? 50 : 100);
		/*
		if(speedR > R) {
			speedR = Math.round(speedR * 100 - accel) / 100;
		} else if (speedR < R) {
			speedR = Math.round(speedR * 100 - accel) / 100;
		}

		if(speedL > L) {
			speedL = Math.round(speedL * 100 - accel) / 100;
		} else if (speedL < L) {
			speedL = Math.round(speedL * 100 + accel) / 100;
		}*/

		speedL = L;
		speedR = R;
		
		// Rounding to prevent floating point addition issues
		// Both FRONT and BACK motors need to have the same speed!!!
		motorLF.set(speedL * percent / 100);
		motorLB.set(speedL * percent / 100);
		motorRF.set(speedR * percent / 100);
		motorRB.set(speedR * percent / 100);
	}

	@Override // When test mode is on
	public void testPeriodic() {}
}
