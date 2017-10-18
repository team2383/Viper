package com.team2384.auto;

import com.team2384.robot.commands.DriveDistance;
import com.team2384.robot.commands.GyroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleGear extends CommandGroup {
	public MiddleGear() {
		addSequential(new DriveDistance(67, 0.60, false, true));
		addSequential(new PlaceGear());
	}
}
 