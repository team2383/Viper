package com.team2384.auto;

import static com.team2384.robot.HAL.feeder;

import com.team2384.ninjaLib.SetState;
import com.team2384.robot.commands.DriveDistance;
import com.team2384.robot.commands.GyroTurn;
import com.team2384.robot.commands.MoveFangs;
import com.team2384.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RightGear extends CommandGroup {
	public RightGear() {
		addSequential(new DriveDistance(62, 0.60, false, true));
		addSequential(new GyroTurn(0.7,-57.5,1.5));
		addSequential(new DriveDistance(72, 0.6, false, true));
		addSequential(new PlaceGear());
	}
}
