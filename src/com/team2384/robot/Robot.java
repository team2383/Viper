	
package com.team2384.robot;


import com.team2384.auto.LeftGear;
import com.team2384.auto.MiddleGear;
import com.team2384.auto.PlaceGear;
import com.team2384.auto.RightGear;
import com.team2384.auto.TestDrive;
import com.team2384.auto.TestTurn;

import com.team2384.ninjaLib.NullCommand;
import com.team2384.robot.commands.GeneralPeriodic;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Command autoCommand;
	Command generalPeriodicCommand = new GeneralPeriodic();
	SendableChooser<Command> autoChooser;
	@SuppressWarnings("unused")
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		HAL hal = new HAL();
		Constants constants = new Constants();
		OI oi = new OI();
		
		
		
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Middle Gear", new MiddleGear());
		autoChooser.addObject("Right Gear", new RightGear());
		autoChooser.addObject("null auto", new NullAuto());
		autoChooser.addObject("Left Gear", new LeftGear());
		autoChooser.addObject("Test Drive", new TestDrive());
		autoChooser.addObject("Test Turn", new TestTurn());
		autoChooser.addObject("Middle Gear", new MiddleGear());
		autoChooser.addObject("Test Gear Place", new PlaceGear());
		SmartDashboard.putData("Auto Chooser", autoChooser);
		
	}

	@Override
	public void disabledInit() {
		if (!generalPeriodicCommand.isRunning()) {
			generalPeriodicCommand.start();
		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		HAL.navX.reset();
		autoCommand = (Command) autoChooser.getSelected();
		if (autoCommand != null) {
			autoCommand.start();
		}

		if (!generalPeriodicCommand.isRunning()) {
			generalPeriodicCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		HAL.drivetrain.resetEncoders();
		HAL.navX.reset();
		
		if (!generalPeriodicCommand.isRunning()) {
			generalPeriodicCommand.start();
		}
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
