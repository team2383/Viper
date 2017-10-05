package com.team2384.auto;

import com.team2384.robot.commands.DriveDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestDrive extends CommandGroup {
	public TestDrive() {
		addSequential(new DriveDistance(108, 0.60, false, true));
	}
}