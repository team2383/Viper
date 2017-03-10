package com.team2383.auto;

import com.team2383.robot.commands.AutoShoot;
import com.team2383.robot.commands.DisableFlap;
import com.team2383.robot.commands.DriveDistance;
import com.team2383.robot.commands.EnableFlap;
import com.team2383.robot.commands.GyroTurn;
import com.team2383.robot.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftWallShot extends CommandGroup {

    public LeftWallShot() {
    	addSequential(new EnableFlap());
        addSequential(new DriveDistance(82, 0.4, Gear.LOW, true));
    	addSequential(new GyroTurn(-90));
    	addSequential(new DriveDistance(33, 0.4, Gear.LOW, true));
    	addSequential(new DisableFlap());
    	addSequential(new AutoShoot(() -> 3190, 12.0));
    }
}