package com.team2384.robot.subsystems;

import java.util.function.DoubleUnaryOperator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team2384.robot.Constants;
import com.team2384.robot.OI;
import com.team2384.robot.commands.HoldFangs;
import com.team2384.robot.commands.MoveFangs;
import com.team2384.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Fangs extends Subsystem {
	private final CANTalon fang = new CANTalon(Constants.kFangsTalonID);
	
	
	public DoubleUnaryOperator mapToZero = (x) -> x - Constants.fangsReverseLimit;
	public DoubleUnaryOperator mapToRaw = (x) -> x + Constants.fangsReverseLimit;

	public Fangs() {
		fang.enableBrakeMode(true);
		fang.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		fang.changeControlMode(TalonControlMode.Position);
		fang.setPID(Constants.kFangPositionP, Constants.kFangPositionI, Constants.kFangPositionD,
				Constants.kFangPositionF, Constants.kFangPositionIZone, 0, 0);
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

	public double get(){
		return fang.getPosition();
	}
	
	public void set(double position){
		fang.setSetpoint(position);
	}
	
	public void resetEncoders() {
		fang.setPosition(0);
	}
	
	public void holdPosition() {
		fang.changeControlMode(TalonControlMode.Position);
		fang.setSetpoint(fang.getPosition());
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new HoldFangs());
	}

}
