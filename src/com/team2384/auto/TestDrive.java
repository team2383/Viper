package com.team2384.auto;

import com.team2384.robot.commands.DriveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestDrive extends CommandGroup {
	public TestDrive() {
		addSequential(new DriveDistance(0, 0.80, false, true));
	}
}