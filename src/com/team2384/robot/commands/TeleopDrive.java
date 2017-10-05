package com.team2384.robot.commands;

import static com.team2384.robot.HAL.drivetrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.team2384.ninjaLib.CheesyDriveHelper;
import com.team2384.robot.HAL;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDrive extends Command {
	private final DoubleSupplier turn;
	private final DoubleSupplier throttle;
	private final CheesyDriveHelper cdh;

	public TeleopDrive(DoubleSupplier throttle, DoubleSupplier turn) {
		super("Teleop Drive");
		requires(drivetrain);
		this.throttle = throttle;
		this.turn = turn;
		this.cdh = new CheesyDriveHelper();
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		cdh.cheesyDrive(drivetrain, throttle.getAsDouble(), turn.getAsDouble());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.tank(0, 0);
	}

	@Override
	protected void interrupted() {
		drivetrain.tank(0, 0);
	}
}
