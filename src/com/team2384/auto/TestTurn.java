package com.team2384.auto;

import com.team2384.robot.commands.DriveDistance;
import com.team2384.robot.commands.GyroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestTurn extends CommandGroup {
	public TestTurn() {
		addSequential(new GyroTurn(60, false));
	}
}