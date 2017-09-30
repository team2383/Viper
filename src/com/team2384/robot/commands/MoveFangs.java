package com.team2384.robot.commands;

import static com.team2384.robot.HAL.fangs;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.team2384.ninjaLib.CheesyDriveHelper;
import com.team2384.robot.HAL;
import com.team2384.robot.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveFangs extends Command {
	private final double speed;

	public MoveFangs(double speed) {
		super("Move Fangs");
		requires(fangs);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	
		fangs.moveAtSpeed(speed);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		fangs.stop();
	}

	@Override
	protected void interrupted() {
		fangs.stop();
	}
}
