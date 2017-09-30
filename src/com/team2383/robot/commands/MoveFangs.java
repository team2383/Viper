package com.team2383.robot.commands;

import static com.team2383.robot.HAL.fangs;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.team2383.ninjaLib.CheesyDriveHelper;
import com.team2383.robot.HAL;
import com.team2383.robot.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveFangs extends Command {
	private final double leftTrigger;
	private final double rightTrigger;

	public MoveFangs(double leftTrigger, double rightTrigger) {
		super("Move Fangs");
		requires(fangs);
		this.leftTrigger = leftTrigger;
		this.rightTrigger = rightTrigger;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	
		double _leftTrigger = leftTrigger;
		double _rightTrigger = rightTrigger;
		
		
		
		if(_leftTrigger == 1.0){
			fangs.moveAtSpeed(0.5);
		}
		if(_rightTrigger == 1.0){
			fangs.moveAtSpeed(0.5);
		}
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
