package com.team2384.auto;

import static com.team2384.robot.HAL.feeder;

import com.team2384.ninjaLib.SetState;
import com.team2384.robot.commands.DriveDistance;
import com.team2384.robot.commands.GyroTurn;
import com.team2384.robot.commands.MoveFangs;
import com.team2384.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class LeftGear extends CommandGroup {
	public LeftGear() {
		addSequential(new DriveDistance(53, 0.80, false, true));
		addSequential(new GyroTurn(0.8,57,1.5));
		addSequential(new DriveDistance(65, 0.80, false, true));
		addSequential(new PlaceGear());
	}
}
