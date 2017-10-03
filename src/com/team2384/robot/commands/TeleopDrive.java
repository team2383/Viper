package com.team2384.robot.commands;

import static com.team2384.robot.HAL.drivetrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.team2384.ninjaLib.CheesyDriveHelper;
import com.team2384.robot.HAL;
import com.team2384.robot.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDrive extends Command {
	private final DoubleSupplier turn;
	private final DoubleSupplier throttle;
	private final CheesyDriveHelper cdh;
	private boolean autoShift;
	private boolean dirty;
	private double timeSlow;
	private double lastCheck;
	private Gear gear;

	public TeleopDrive(DoubleSupplier throttle, DoubleSupplier turn) {
		super("Teleop Drive");
		requires(drivetrain);
		this.throttle = throttle;
		this.turn = turn;
		this.autoShift = false;
		this.gear = Gear.LOW;
		this.dirty = false;
		this.timeSlow = 0.0;
		this.lastCheck = 0.0;
		this.cdh = new CheesyDriveHelper();
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		cdh.cheesyDrive(drivetrain, -throttle.getAsDouble()/1.8, -turn.getAsDouble()/1.8, gear == Gear.HIGH);
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
