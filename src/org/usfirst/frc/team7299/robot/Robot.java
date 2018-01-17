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
	public final int buttonA = 1;
	public final int buttonB = 2;
	public final int buttonX = 3;
	public final int buttonY = 4;
	public final int buttonLB = 5;
	public final int buttonRB = 6;

	//Compressor compressor = new Compressor();

	Joystick joystick = new Joystick(0);

	//DoubleSolenoid solenoid1 = new DoubleSolenoid(1,2);
	//DoubleSolenoid solenoid2 = new DoubleSolenoid(3,4);

	Spark motorLF = new Spark(0);
	Spark motorLB = new Spark(1);
	Spark motorRF = new Spark(2);
	Spark motorRB = new Spark(3);
	Spark conveyor = new Spark(4);
	Spark wheel = new Spark(5);

	boolean solenoidOn = false;
	boolean slowMode = false;

	@Override // Before anything
	public void robotInit() {
		//compressor.setClosedLoopControl(true);
		//solenoid1.set(DoubleSolenoid.Value.kReverse);
		//solenoid2.set(DoubleSolenoid.Value.kReverse);
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

		if(joystick.getRawButtonReleased(buttonA)) {
			if(solenoidOn){
				solenoid1.set(DoubleSolenoid.Value.kReverse);
				solenoid2.set(DoubleSolenoid.Value.kReverse);
			} else {
				solenoid1.set(DoubleSolenoid.Value.kForward);
				solenoid2.set(DoubleSolenoid.Value.kForward);
			}
			solenoidOn = !solenoidOn;
		}*/

		double x = joystick.getX();
		double y = joystick.getY();

		double R = ((y + x) / Math.sqrt(2));
		double L = ((y - x) / Math.sqrt(2));

		if(joystick.getRawButtonReleased(buttonY)) {
			slowMode = !slowMode;
			System.out.println("SlowMode = " + slowMode);
		}
		double percent = (slowMode? 50 : 100);

		motorLF.set(-L * percent / 100);
		motorLB.set(-L * percent / 100);
		motorRF.set(-R * percent / 100);
		motorRB.set(-R * percent / 100);
		
		if(joystick.getRawButton(buttonA)) {
			conveyor.set(0.5);
		}
		else if (joystick.getRawButton(buttonB)) {
			conveyor.set(-0.5);
		}
		else {
			conveyor.set(0);
		}
		
		if(joystick.getRawButton(buttonX)) {
			wheel.set(1);
		}
		else {
			wheel.set(0);
		}
	}

	@Override // When test mode is on
	public void testPeriodic() {
		/*
		 *   ### SOLENOID TEST CODE ###
		 *

		if(joystick.getRawButtonReleased(buttonA)) {
			if(solenoidOn){
				solenoid1.set(DoubleSolenoid.Value.kReverse);
				solenoid2.set(DoubleSolenoid.Value.kReverse);
			} else {
				solenoid1.set(DoubleSolenoid.Value.kForward);
				solenoid2.set(DoubleSolenoid.Value.kForward);
			}
			solenoidOn = !solenoidOn;
		}*/
		double x = joystick.getX();
		double y = joystick.getY();

		double R = ((y + x) / Math.sqrt(2));
		double L = ((y - x) / Math.sqrt(2));

		if(joystick.getRawButtonReleased(buttonY)) {
			slowMode = !slowMode;
			System.out.println("SlowMode = " + slowMode);
		}
		double percent = (slowMode? 50 : 100);

		motorLF.set(-L * percent / 100);
		motorLB.set(-L * percent / 100);
		motorRF.set(-R * percent / 100);
		motorRB.set(-R * percent / 100);
	}
}
