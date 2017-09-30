package com.team2384.auto;

import com.team2384.robot.Constants;
import com.team2384.robot.commands.AutoDriveStraight;
import com.team2384.robot.commands.DriveDistance;
import com.team2384.robot.commands.GyroTurn;
import com.team2384.robot.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestDrive extends CommandGroup {
	public TestDrive() {
		addSequential(new DriveDistance(125, 0.8, Gear.HIGH, true));
		
	
		
	}
}