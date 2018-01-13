/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7299.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends IterativeRobot {
	
	public static final int buttonA = 1;
	public static final int buttonB = 2;
	public static final int buttonX = 3;
	public static final int buttonY = 4;
	public static final int buttonLB = 5;
	public static final int buttonRB = 6;

	//Compressor compressor = new Compressor();
	
	Joystick joystick = new Joystick(0);
	
	//DoubleSolenoid solenoid1 = new DoubleSolenoid(1,2);
	//DoubleSolenoid solenoid2 = new DoubleSolenoid(3,4);
	
	Spark motorLF = new Spark(0);
	Spark motorLB = new Spark(1);
	Spark motorRF = new Spark(2);
	Spark motorRB = new Spark(3);
	
	double speedL = 0;
	double speedR = 0;
	double speedComp = 0;
	
	final double accel = 0.1;
	
	boolean solenoidOn = false;
	boolean slowMode = false;

	@Override // Before anything
	public void robotInit() {
		//compressor.setClosedLoopControl(true);
		//solenoid1.set(DoubleSolenoid.Value.kReverse);
		//solenoid2.set(DoubleSolenoid.Value.kReverse);
	}

	@Override // Before teleop control
	public void autonomousInit() {}

	@Override // During teleop control
	public void autonomousPeriodic() {}
	
	@Override // During operator control
	public void teleopPeriodic() {

		/* SOLENOID CONTROL
		if (joystick.getRawButtonReleased(buttonA)){
			if (solenoidOn){
				solenoid1.set(DoubleSolenoid.Value.kReverse);
				solenoid2.set(DoubleSolenoid.Value.kReverse);
			}
			else{
				solenoid1.set(DoubleSolenoid.Value.kForward);
				solenoid2.set(DoubleSolenoid.Value.kForward);
			}
			solenoidOn = !solenoidOn;
		}*/
		
		double x = joystick.getX();
		double y = joystick.getY();
		
		double R = ((y + x) / Math.sqrt(2));
		double L = ((y - x) / Math.sqrt(2));
		
		speedL = L;
		speedR = R;
		
		/*
		if(speedL > L) {
			speedL = Math.round(speedL * 100 - accel) / 100;
		} else if(speedL < L) {
			speedL = Math.round(speedL * 100 + accel) / 100;
		}
		
		if(speedR > R) {
			speedR = Math.round(speedR * 100 - accel) / 100;
		} else if(speedR < R) {
			speedR = Math.round(speedR * 100 + accel) / 100;
		}*/
		
		if (joystick.getRawButtonReleased(buttonY))
		{	
			slowMode = !slowMode;
			System.out.println("SlowMode = " + slowMode);
		}
		
		if (slowMode) {
			speedL = speedL / 2;
			speedR = speedR / 2;
		}
		
		// Rounding to prevent floating point addition issues
		// Both FRONT and BACK motors need to have the same speed!!!
		motorLF.set(-(speedL));
		motorLB.set(-(speedL));
		motorRF.set(-(speedR));
		motorRB.set(-(speedR));
	}

	@Override // When test mode is on
	public void testPeriodic() {
		
		/* SOLENOID CONTROL
		if (joystick.getRawButtonReleased(buttonA)){
			if (solenoidOn){
				solenoid1.set(DoubleSolenoid.Value.kReverse);
				solenoid2.set(DoubleSolenoid.Value.kReverse);
			}
			else{
				solenoid1.set(DoubleSolenoid.Value.kForward);
				solenoid2.set(DoubleSolenoid.Value.kForward);
			}
			solenoidOn = !solenoidOn;
		}*/
		
		double x = joystick.getX();
		double y = joystick.getY();
		
		double R = ((y + x) / Math.sqrt(2));
		double L = ((y - x) / Math.sqrt(2));
		
		speedL = L;
		speedR = R;
		
		/*
		if(speedL > L) {
			speedL = Math.round(speedL * 100 - accel) / 100;
		} else if(speedL < L) {
			speedL = Math.round(speedL * 100 + accel) / 100;
		}
		
		if(speedR > R) {
			speedR = Math.round(speedR * 100 - accel) / 100;
		} else if(speedR < R) {
			speedR = Math.round(speedR * 100 + accel) / 100;
		}*/
		
		if (joystick.getRawButtonReleased(buttonY))
		{	
			slowMode = !slowMode;
			System.out.println("SlowMode = " + slowMode);
		}
		
		if (slowMode) {
			speedL = speedL / 2;
			speedR = speedR / 2;
		}
			
		// Rounding to prevent floating point addition issues
		// Both FRONT and BACK motors need to have the same speed!!!
		motorLF.set(-(speedL));
		motorLB.set(-(speedL));
		motorRF.set(-(speedR));
		motorRB.set(-(speedR));
	}
}
