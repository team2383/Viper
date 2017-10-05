package com.team2384.auto;

import static com.team2384.robot.HAL.feeder;

import com.team2384.ninjaLib.SetState;
import com.team2384.robot.commands.DriveDistance;
import com.team2384.robot.commands.MoveFangs;
import com.team2384.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PlaceGear extends CommandGroup {
	public PlaceGear() {
		addParallel(new MoveFangs(-0.5), 0.65);
		addSequential(new WaitCommand(0.1));
		addParallel(new SetState<Feeder.State>(feeder, Feeder.State.UNFEED, Feeder.State.STOPPED), 2);
		addSequential(new WaitCommand(0.35));
		addSequential(new DriveDistance(-20, 0.6, false, true));
	}
}

