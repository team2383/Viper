package com.team2383.robot.subsystems;

import com.team2383.robot.Constants;
import com.team2383.robot.OI;
import com.team2383.robot.commands.PrecisionDrive;
import com.team2383.robot.commands.TeleopDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem implements PIDSource{
	private final RobotDrive robotDrive;
	public final CANTalon leftMaster;
	private final CANTalon leftSlaveOne;
	private final CANTalon leftSlaveTwo;
	private final CANTalon leftSlaveThree;

	public final CANTalon rightMaster;
	private final CANTalon rightSlaveOne;
	private final CANTalon rightSlaveTwo;
	private final CANTalon rightSlaveThree;

	public enum Gear {
		LOW, HIGH;

		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}

	public Drivetrain() {
		super("Drivetrain");
		
		/*
		 * Initialize drive talons
		 */
		
		leftMaster = new CANTalon(Constants.kLeftMasterTalonID);
		leftSlaveOne = new CANTalon(Constants.kLeftSlaveOneTalonID);
		leftSlaveTwo = new CANTalon(Constants.kLeftSlaveTwoTalonID);
		leftSlaveThree = new CANTalon(Constants.kLeftSlaveThreeTalonID);
		
		rightMaster = new CANTalon(Constants.kRightMasterTalonID);
		rightSlaveOne = new CANTalon(Constants.kRightSlaveOneTalonID);
		rightSlaveTwo = new CANTalon(Constants.kRightSlaveTwoTalonID);
		rightSlaveThree = new CANTalon(Constants.kRightSlaveThreeTalonID);

		
		/*
		 * Configure drive talons 
		 */
		leftMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftMaster.reverseSensor(false);
		leftMaster.setPID(Constants.kDriveHoldPositionP, Constants.kDriveHoldPositionI, Constants.kDriveHoldPositionD,
				Constants.kDriveHoldPositionF, Constants.kDriveHoldPositionIZone, 0, 1);
		leftSlaveOne.changeControlMode(TalonControlMode.Follower);
		leftSlaveOne.set(leftMaster.getDeviceID());
		leftSlaveTwo.changeControlMode(TalonControlMode.Follower);
		leftSlaveTwo.set(leftMaster.getDeviceID());
		leftSlaveThree.changeControlMode(TalonControlMode.Follower);
		leftSlaveThree.set(leftMaster.getDeviceID());

		rightMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightMaster.reverseSensor(true);
		rightMaster.setPID(Constants.kDriveHoldPositionP, Constants.kDriveHoldPositionI, Constants.kDriveHoldPositionD,
				Constants.kDriveHoldPositionF, Constants.kDriveHoldPositionIZone, 0, 1);
		rightSlaveOne.changeControlMode(TalonControlMode.Follower);
		rightSlaveOne.set(rightMaster.getDeviceID());
		rightSlaveTwo.changeControlMode(TalonControlMode.Follower);
		rightSlaveTwo.set(rightMaster.getDeviceID());
		rightSlaveThree.changeControlMode(TalonControlMode.Follower);
		rightSlaveThree.set(rightMaster.getDeviceID());
		
		this.robotDrive = new RobotDrive(leftMaster, rightMaster);
		robotDrive.setSafetyEnabled(false);
	}

	public void tank(double leftValue, double rightValue) {
		leftMaster.changeControlMode(TalonControlMode.PercentVbus);
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
		robotDrive.tankDrive(rightValue, leftValue);
	}

	public void arcade(double driveSpeed, double turnSpeed) {
		leftMaster.changeControlMode(TalonControlMode.PercentVbus);
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
		robotDrive.arcadeDrive(driveSpeed, -turnSpeed);
	}


	public void resetEncoders() {
		leftMaster.setPosition(0);
		rightMaster.setPosition(0);
	}



	public double getRotations() {
		double rotations;
		if (leftMaster.isSensorPresent(
				FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent)
			return 0;
		if (rightMaster.isSensorPresent(
				FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent)
			return 0;
		try {
			rotations = (leftMaster.getPosition() + rightMaster.getPosition()) / 2.0;
		} catch (Throwable e) {
			System.out.println("Failed to get encoder rotations of drivetrain");
			rotations = 0;
		}
		return rotations;
	}

	public double getVelocity() {
		double rotations;
		if (leftMaster.isSensorPresent(
				FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent)
			return 0;
		if (rightMaster.isSensorPresent(
				FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent)
			return 0;
		try {
			rotations = (leftMaster.getSpeed() + rightMaster.getSpeed())/2.0;
		} catch (Throwable e) {
			System.out.println("Failed to get encoder speed of drivetrain");
			rotations = 0;
		}
		return rotations;
	}
	
	
	public double getError(){
		double error;
		if (leftMaster.isSensorPresent(
				FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent)
			return 0;
		if (rightMaster.isSensorPresent(
				FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent)
			return 0;
		try {
			error = (rightMaster.getClosedLoopError() + leftMaster.getClosedLoopError()) / 2;
		} catch (Throwable e) {
			System.out.println("Failed to get error of drivetrain");
			error = 0;
		}
		return error;
	}

	public double getInches() {
		return getRotations() * Constants.kDriveWheelCircumference;
	}

	// Feet per Seconds
	public double getSpeed() {
		return getVelocity() * Constants.kDriveWheelCircumference / 12.0 / 60.0;
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TeleopDrive(OI.throttle, OI.turn, OI.toggleAutoShift::get, OI.shiftDown::get, OI.shiftUp::get));
	}

	public void setBrake(boolean brake) {
		leftMaster.enableBrakeMode(brake);
		rightMaster.enableBrakeMode(brake);
	}

	public void enableBrake() {
		setBrake(true);
	}

	public void disableBrake() {
		setBrake(false);
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getInches();
	}
	
	public void holdPosition() {
		leftMaster.setProfile(1);
		leftMaster.changeControlMode(TalonControlMode.Position);
		leftMaster.setSetpoint(leftMaster.getPosition());

		rightMaster.setProfile(1);
		rightMaster.changeControlMode(TalonControlMode.Position);
		rightMaster.setSetpoint(rightMaster.getPosition());
	}

	
	
}
