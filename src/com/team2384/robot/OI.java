package com.team2384.robot;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;


import com.team2384.ninjaLib.DPadButton;
import com.team2384.ninjaLib.DPadButton.Direction;
import com.team2384.ninjaLib.Gamepad;
import com.team2384.ninjaLib.LambdaButton;
import com.team2384.ninjaLib.OnChangeButton;
import com.team2384.ninjaLib.SetState;
import com.team2384.ninjaLib.Values;
import com.team2384.ninjaLib.WPILambdas;
import com.team2384.robot.subsystems.Fangs;
import com.team2384.robot.subsystems.Feeder;
import com.team2384.robot.commands.AutoDriveStraight;
import com.team2384.robot.commands.MoveFangs;

import com.team2384.robot.commands.MoveFangs;
import com.team2384.robot.commands.PrecisionDrive;

import com.team2384.robot.commands.TeleopDriveStraight;

import com.team2384.robot.subsystems.Climber;
import com.team2384.ninjaLib.SetState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;


import static com.team2384.robot.HAL.fangs;
import static com.team2384.robot.HAL.feeder;
import static com.team2384.robot.HAL.climber;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

/*
 * OI Controls:
 * 
 *  Advanced Operator Controls: 
 *  Left Trigger: Fangs Down
 *  Right Trigger: Fangs Up
 *  
 *  Left Trigger: Feed Out
 *	Right Trigger: Feed In
 *
 *	X: Climb
 *
 *	Left Stick Y : Forwards/Backwards
 *	Right Stick X : Left/Right 
 */
@SuppressWarnings("unused")
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.

	/* Sticks functions */

	private static DoubleUnaryOperator inputExpo = (x) -> {
		return Constants.inputExpo * Math.pow(x, 3) + (1 - Constants.inputExpo) * x;
	};
	
	private static DoubleUnaryOperator deadband = (x) -> {
		return Math.abs(x) > Constants.inputDeadband ? x : 0;
	};
	
	
	// All-in-one
	public static Gamepad advancedOperator = new Gamepad(4);
	
	public static DoubleSupplier throttle = () -> deadband.applyAsDouble(advancedOperator.getLeftY());
	public static DoubleSupplier turn = () -> deadband.applyAsDouble(advancedOperator.getRightX());
	
	public static Button climb = new JoystickButton(advancedOperator, 3);
	public static Button leftTrigger = advancedOperator.getLeftTriggerClick();
	public static Button rightTrigger = advancedOperator.getRightTriggerClick();
	
	public static Button leftBumper = advancedOperator.getLeftShoulder();
	public static Button rightBumper = advancedOperator.getRightShoulder();
	
	public static Button precisionDrive = new JoystickButton(advancedOperator,2);
	
	public static Button driveStraight = new JoystickButton(advancedOperator,4);
	
	


	
	public OI() {
		
		precisionDrive.toggleWhenPressed(new PrecisionDrive(throttle, turn));
		
		//driveStraight.toggleWhenPressed(new TeleopDriveStraight(throttle));
		climb.toggleWhenPressed(new SetState<Climber.State>(climber, Climber.State.CLIMB, Climber.State.STOPPED));
		//climb.whileHeld(new SetState<Climber.State>(climber, Climber.State.CLIMB, Climber.State.STOPPED));
		
		leftTrigger.whileHeld(new MoveFangs(-0.4));
		rightTrigger.whileHeld(new MoveFangs(0.6));
		
		leftBumper.whileHeld(new SetState<Feeder.State>(feeder, Feeder.State.UNFEED, Feeder.State.STOPPED));
		rightBumper.whileHeld(new SetState<Feeder.State>(feeder, Feeder.State.FEED, Feeder.State.STOPPED));
		
	}
}
