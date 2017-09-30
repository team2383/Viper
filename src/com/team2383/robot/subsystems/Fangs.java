package com.team2383.robot.subsystems;

import java.util.function.DoubleUnaryOperator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team2383.robot.Constants;
import com.team2383.robot.OI;
import com.team2383.robot.commands.MoveFangs;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Fangs extends Subsystem {
	private final CANTalon fang = new CANTalon(Constants.kFangsTalonID);
	
	
	public DoubleUnaryOperator mapToZero = (x) -> x - Constants.fangsReverseLimit;
	public DoubleUnaryOperator mapToRaw = (x) -> x + Constants.fangsReverseLimit;

	public Fangs() {
		fang.enableBrakeMode(true);
		fang.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		fang.changeControlMode(TalonControlMode.Position);
		
		fang.setReverseSoftLimit(Constants.fangsReverseLimit);
		fang.setForwardSoftLimit(Constants.fangsForwardLimit);
		fang.enableForwardSoftLimit(false);
		fang.enableReverseSoftLimit(false);
		fang.configPeakOutputVoltage(6.0, -6.0);
		fang.reverseOutput(false);
		fang.reverseSensor(false);
	}

	public void moveAtSpeed(double speed) {
		fang.changeControlMode(TalonControlMode.PercentVbus);
		fang.set(speed);
	}

	public void stop() {
		fang.changeControlMode(TalonControlMode.PercentVbus);
		fang.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new MoveFangs(OI.leftTrigger, OI.rightTrigger));
	}

}
